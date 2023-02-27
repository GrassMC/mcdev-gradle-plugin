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
 * DSL extension that is used to configure platform api enviroment for the entire project.
 */
interface PlatformDevExtension {
    /**
     * The platform vendor provides their development api.
     */
    val apiVendor: PlatformVendor

    /**
     * Version of the [apiVendor] libraries that are added to compile classpath.
     */
    val apiVersion: Version
}
