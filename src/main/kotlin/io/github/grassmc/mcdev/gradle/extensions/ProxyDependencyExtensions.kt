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

package io.github.grassmc.mcdev.gradle.extensions

import io.github.grassmc.mcdev.gradle.version.Version
import org.gradle.api.artifacts.dsl.DependencyHandler

internal object ProxyDependencies {
    val VELOCITY_API = DependencyHolder("com.velocitypowered", "velocity-api", true)
    val BUNGEE_CORD_API = DependencyHolder("net.md-5", "bungeecord-api", true, ServerDependencies.R0_1_SUFFIX)
    val WATERFALL_API = DependencyHolder("io.github.waterfallmc", "waterfall-api", true, ServerDependencies.R0_1_SUFFIX)
}

fun DependencyHandler.velocityApi(version: String) = ProxyDependencies.VELOCITY_API.notation(version)

fun DependencyHandler.velocityApi(version: Version) = velocityApi(version.asString())

fun DependencyHandler.bungeecordApi(version: String) = ProxyDependencies.BUNGEE_CORD_API.notation(version)

fun DependencyHandler.bungeecordApi(version: Version) = bungeecordApi(version.asString())

fun DependencyHandler.waterfallApi(version: String) = ProxyDependencies.WATERFALL_API.notation(version)

fun DependencyHandler.waterfallApi(version: Version) = waterfallApi(version.asString())
