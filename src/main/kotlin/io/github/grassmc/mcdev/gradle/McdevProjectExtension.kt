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
import org.gradle.api.Project
import javax.inject.Inject

abstract class McdevProjectExtension @Inject constructor(private val project: Project) : McdevPlatformConfig {
    private lateinit var _apiVendor: PlatformVendor

    @set:Deprecated(USE_PLUGIN_INSTEAD_WARN_MESSAGE)
    override var apiVendor: PlatformVendor
        get() = _apiVendor
        set(value) {
            _apiVendor = value
            project.warnUsePluginInstead()
        }
    override lateinit var apiVersion: Version

    /**
     * Sets [apiVendor] with given [vendor] as default value.
     */
    internal fun defaultVendor(vendor: PlatformVendor) {
        _apiVendor = vendor
    }

    private fun Project.warnUsePluginInstead() = afterEvaluate { logger.lifecycle(USE_PLUGIN_INSTEAD_WARN_MESSAGE) }

    companion object {
        private const val USE_PLUGIN_INSTEAD_WARN_MESSAGE =
            "Please use the plugin available for each platform instead of changing the apiVendor."
    }
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
