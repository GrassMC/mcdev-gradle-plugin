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

import io.github.grassmc.mcdev.gradle.extensions.DependencyHolder
import io.github.grassmc.mcdev.gradle.extensions.ProxyDependencies
import io.github.grassmc.mcdev.gradle.extensions.ServerDependencies
import io.github.grassmc.mcdev.gradle.version.MinecraftVersion
import io.github.grassmc.mcdev.gradle.version.Version

abstract class McdevProjectExtension : McdevPlatformConfig {
    override lateinit var apiVendor: PlatformVendor
    override lateinit var apiVersion: Version
}

/**
 * List of server platform vendors.
 */
enum class ServerVendor(
    override val displayName: String,
    private val notationSupplier: (version: MinecraftVersion) -> String,
) : PlatformVendor {
    SpigotMC("Spigot", ServerDependencies::spigotApiNotation),
    PaperMC("Paper", ServerDependencies::paperApiNotation),
    PurpurMC("Purpur", ServerDependencies::purpurApiNotation);

    override fun dependencyNotation(version: Version) =
        notationSupplier(version as? MinecraftVersion ?: MinecraftVersion.matching(version))
}

/**
 * List of proxy platform vendors.
 */
enum class ProxyVendor(
    override val displayName: String,
    private val dependency: DependencyHolder,
) : PlatformVendor {
    Velocity("Velocity", ProxyDependencies.VELOCITY_API),
    BungeeCord("BungeeCord", ProxyDependencies.BUNGEE_CORD_API),
    Waterfall("Waterfall", ProxyDependencies.WATERFALL_API);

    override fun dependencyNotation(version: Version) = dependency.notation(version.asString())
}
