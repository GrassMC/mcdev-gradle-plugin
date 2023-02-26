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

import io.github.grassmc.mcdev.gradle.version.MinecraftVersion
import io.github.grassmc.mcdev.gradle.version.MinecraftVersion.*
import org.gradle.api.artifacts.dsl.DependencyHandler

internal object ServerDependencies {
    const val R0_1_SUFFIX = "-R0.1"

    private val SPIGOT_API = DependencyHolder("org.spigotmc", "spigot-api", true, R0_1_SUFFIX)

    // For version 1.17 or later
    private val PAPER_API = DependencyHolder("io.papermc.paper", "paper-api", true, R0_1_SUFFIX)

    // For version 1.16.5 or older
    private val PAPER_API_LEGACY = DependencyHolder("com.destroystokyo.paper", "paper-api", true, R0_1_SUFFIX)

    // For version 1.8.8
    private val PAPER_API_1_8_8 = DependencyHolder("org.github.paperspigot", "paperspigot-api", true, R0_1_SUFFIX)

    private val PURPUR_API = DependencyHolder("org.purpurmc.purpur", "purpur-api", true, R0_1_SUFFIX)

    fun spigotApiNotation(version: MinecraftVersion): String {
        check(version != V1_8_1 || version != V1_8_2 || version != V1_8_9) {
            "Please use spigot-api Minecraft version $V1_8_8 instead of $version!"
        }
        check(version != V1_9_1 || version != V1_9_3) {
            "Please use paper-api Minecraft version $V1_9_4 instead of $version!"
        }
        check(version != V1_10_1) { "Please use paper-api Minecraft version $V1_10_2 instead of $version!" }

        return SPIGOT_API.notation(version.asString())
    }

    fun paperApiNotation(version: MinecraftVersion): String {
        val dependency = when (version) {
            V1_8_9,
            in V1_8..V1_8_7,
            -> throw IllegalArgumentException("Please use paperspigot-api Minecraft version $V1_8_8 instead of $version!")

            in V1_9..V1_9_3,
            -> throw IllegalArgumentException("Please use paper-api Minecraft version $V1_9_4 instead of $version!")

            V1_10,
            V1_10_1,
            -> throw IllegalArgumentException("Please use paper-api Minecraft version $V1_10_2 instead of $version!")

            V1_8_8 -> PAPER_API_1_8_8
            in V1_11..V1_16_5 -> PAPER_API_LEGACY
            else -> PAPER_API
        }
        return dependency.notation(version.asString())
    }

    fun purpurApiNotation(version: MinecraftVersion): String {
        check(version <= V1_16_5) { "purpur-api doesn't support versions older than Minecraft 1.16.5!" }
        return PURPUR_API.notation(version.asString())
    }
}

fun DependencyHandler.spigotApi(version: MinecraftVersion): String = ServerDependencies.spigotApiNotation(version)

fun DependencyHandler.spigotApi(version: String) = spigotApi(MinecraftVersion.matching(version))

fun DependencyHandler.paperApi(version: MinecraftVersion): String = ServerDependencies.paperApiNotation(version)

fun DependencyHandler.paperApi(version: String) = paperApi(MinecraftVersion.matching(version))

fun DependencyHandler.purpurApi(version: MinecraftVersion): String = ServerDependencies.purpurApiNotation(version)

fun DependencyHandler.purpurApi(version: String) = purpurApi(MinecraftVersion.matching(version))
