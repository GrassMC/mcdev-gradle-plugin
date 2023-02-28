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

interface PlatformVendor {
    val displayName: String

    fun apiNotation(version: Version): String
}

enum class ServerVendor(
    override val displayName: String,
    private val apiNotationSupplier: (version: MinecraftVersion) -> String,
) : PlatformVendor {
    SpigotMC("Spigot", ServerDependencies::spigotApiNotation),
    PaperMC("Paper", ServerDependencies::paperApiNotation),
    PurpurMC("Purpur", ServerDependencies::purpurApiNotation);

    override fun apiNotation(version: Version): String =
        apiNotationSupplier(version as? MinecraftVersion ?: MinecraftVersion.matching(version))
}

enum class ProxyVendor(
    override val displayName: String,
    private val apiDependency: DependencyHolder,
) : PlatformVendor {
    Velocity("Velocity", ProxyDependencies.VELOCITY_API),
    BungeeCord("Bungee", ProxyDependencies.BUNGEE_CORD_API),
    Waterfall("Waterfall", ProxyDependencies.WATERFALL_API);

    override fun apiNotation(version: Version): String = apiDependency.notation(version.asString())
}
