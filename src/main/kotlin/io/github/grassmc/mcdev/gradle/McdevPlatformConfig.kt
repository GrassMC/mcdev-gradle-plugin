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

package io.github.grassmc.mcdev.gradle

import io.github.grassmc.mcdev.gradle.version.Version

/**
 * DSL extension that is used to configure the platform api environment for the entire project.
 */
interface McdevPlatformConfig {
    /**
     * The platform vendor provides their development api.
     */
    var apiVendor: PlatformVendor

    /**
     * Version of the [apiVendor] libraries that are added to compile classpath.
     */
    var apiVersion: Version
}

/**
 * Represents a vendor of platform software.
 *
 * @see ServerVendor
 * @see ProxyVendor
 */
interface PlatformVendor {
    /**
     * Gets the display name of the software.
     */
    val displayName: String

    /**
     * Builds the dependency notation for the platform api at the given [version].
     */
    fun dependencyNotation(version: Version): String
}
