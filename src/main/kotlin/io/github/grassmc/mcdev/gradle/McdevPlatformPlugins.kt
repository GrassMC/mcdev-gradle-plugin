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

import io.github.grassmc.mcdev.gradle.version.MinecraftVersion
import io.github.grassmc.mcdev.gradle.version.Version
import io.github.grassmc.mcdev.gradle.version.from

/**
 * The `io.github.grassmc.mcdev.spigot` plugin.
 */
abstract class McdevSpigotPlugin : McdevPlatformPluginBase(ServerVendor.SpigotMC.displayName) {
    override fun configDefaultProjectExtension(extension: McdevProjectExtension) = extension.run {
        defaultVendor(ServerVendor.SpigotMC)
        apiVersion = MinecraftVersion.LATEST
    }
}

/**
 * The `io.github.grassmc.mcdev.paper` plugin.
 */
abstract class McdevPaperPlugin : McdevPlatformPluginBase(ServerVendor.PaperMC.displayName) {
    override fun configDefaultProjectExtension(extension: McdevProjectExtension) = extension.run {
        defaultVendor(ServerVendor.PaperMC)
        apiVersion = MinecraftVersion.LATEST
    }
}

/**
 * The `io.github.grassmc.mcdev.purpur` plugin.
 */
abstract class McdevPurpurPlugin : McdevPlatformPluginBase(ServerVendor.PurpurMC.displayName) {
    override fun configDefaultProjectExtension(extension: McdevProjectExtension) = extension.run {
        defaultVendor(ServerVendor.PurpurMC)
        apiVersion = MinecraftVersion.LATEST
    }
}

/**
 * The `io.github.grassmc.mcdev.velocity` plugin.
 */
abstract class McdevVelocityPlugin : McdevPlatformPluginBase(ProxyVendor.Velocity.displayName) {
    override fun configDefaultProjectExtension(extension: McdevProjectExtension) = extension.run {
        defaultVendor(ProxyVendor.Velocity)
        apiVersion = VELOCITY_API_LATEST_VERSION
    }

    companion object {
        val VELOCITY_API_LATEST_VERSION = Version.from("3.2.0-SNAPSHOT")
    }
}

/**
 * The `io.github.grassmc.mcdev.bungeecord` plugin.
 */
abstract class McdevBungeeCordPlugin : McdevPlatformPluginBase(ProxyVendor.BungeeCord.displayName) {
    override fun configDefaultProjectExtension(extension: McdevProjectExtension) = extension.run {
        defaultVendor(ProxyVendor.BungeeCord)
        apiVersion = BUNGEECORD_API_LATEST_VERSION
    }

    companion object {
        val BUNGEECORD_API_LATEST_VERSION = Version.from("1.19-R0.1-SNAPSHOT")
    }
}

/**
 * The `io.github.grassmc.mcdev.waterfall` plugin.
 */
abstract class McdevWaterfallPlugin : McdevPlatformPluginBase(ProxyVendor.Waterfall.displayName) {
    override fun configDefaultProjectExtension(extension: McdevProjectExtension) = extension.run {
        defaultVendor(ProxyVendor.Waterfall)
        apiVersion = McdevBungeeCordPlugin.BUNGEECORD_API_LATEST_VERSION
    }
}
