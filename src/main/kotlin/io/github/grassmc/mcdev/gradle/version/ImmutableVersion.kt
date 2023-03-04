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

private data class ImmutableVersion(val version: String) : Version {
    override fun asString(): String = version

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ImmutableVersion) return false
        return this.version == other.version
    }

    override fun hashCode(): Int = version.hashCode()

    override fun toString(): String = asString()
}

/**
 * Creates new an instance of [Version] by given [version] string.
 */
fun Version.Companion.from(version: String): Version = ImmutableVersion(version)
