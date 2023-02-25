/*
 * Copyright 2023 GrassMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.grassmc.mcdev.gradle.version

import io.github.grassmc.mcdev.gradle.version.DefaultVersion.Companion.SNAPSHOT_SUFFIX
import kotlin.math.max

/**
 * A parsed version with common format.
 *
 * [major], [minor] and [patch] are integer components of a versions, they must be positive number
 * (excepts [patch] can be negative).
 * If [patch] is negative, the [patch] component is not present in [asString].
 *
 * When [snapshot] is true, the [SNAPSHOT_SUFFIX] will append to the end of [asString].
 *
 * @constructor Creates a version from all three components.
 */
class DefaultVersion(val major: Int, val minor: Int, val patch: Int, val snapshot: Boolean = false) : Version {
    /**
     * Creates a version from [major] and [minor] components, leaving [patch] component `-1`.
     */
    constructor(major: Int, minor: Int, snapshot: Boolean = false) : this(major, minor, -1, snapshot)

    private val version = versionOf(major, minor, patch, snapshot)
    private val versionString = stringVersionOf(major, minor, patch, snapshot)

    override fun asString(): String = versionString

    override fun compareTo(other: Version): Int =
        if (other is DefaultVersion) version - other.version else -other.compareTo(this)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DefaultVersion) return false
        return this.version == other.version
    }

    override fun hashCode(): Int = version

    override fun toString(): String = asString()

    companion object {
        const val SNAPSHOT_SUFFIX = "-SNAPSHOT"

        /**
         * Parses the [version] string into a [Version] object.
         *
         * Currently accepted format: `<major>.<minor>[.<patch>][-SNAPSHOT]`. E.g: `1.0`, `1.0-SNAPSHOT`,
         * `1.0.2-SNAPSHOT`,...
         */
        fun parse(version: String): DefaultVersion {
            val snapshot = version.endsWith(SNAPSHOT_SUFFIX)
            val verWithoutSnapshot = version.substring(0 until version.lastIndexOf(SNAPSHOT_SUFFIX))
            val parts = verWithoutSnapshot.split(Regex("\\."), 3)

            if (parts.size > 2) return DefaultVersion(parts[0].toInt(), parts[1].toInt(), parts[2].toInt(), snapshot)
            return DefaultVersion(parts[0].toInt(), parts[1].toInt(), snapshot)
        }

        private fun versionOf(major: Int, minor: Int, patch: Int, snapshot: Boolean) =
            major.shl(16) + minor.shl(8) + max(0, patch) + snapshot.compareTo(false)

        private fun stringVersionOf(major: Int, minor: Int, patch: Int, snapshot: Boolean) =
            buildString {
                append(major)
                append('.')
                append(minor)
                if (patch >= 0) append('.').append(patch)
                if (snapshot) append(SNAPSHOT_SUFFIX)
            }
    }
}
