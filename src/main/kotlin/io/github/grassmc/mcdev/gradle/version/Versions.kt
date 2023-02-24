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

/**
 * A parsed artifact version.
 *
 * @see DefaultVersion
 */
interface Version : Comparable<Version> {
    /**
     * Gets the original string representation of the version.
     */
    fun asString(): String
}

/**
 * A container object provides a version.
 *
 * @see VersionProvider.of
 * @see VersionProvider.from
 */
interface VersionProvider {
    /**
     * Gets the [version] represent the container.
     */
    val version: Version
}
