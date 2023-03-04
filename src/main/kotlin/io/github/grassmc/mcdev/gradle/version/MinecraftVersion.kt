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
 * List of Minecraft versions from 1.8 to latest.
 */
enum class MinecraftVersion(private val versionString: String) : Version {
    V1_8("1.8"),
    V1_8_1("1.8.1"),
    V1_8_2("1.8.2"),
    V1_8_3("1.8.3"),
    V1_8_4("1.8.4"),
    V1_8_5("1.8.5"),
    V1_8_6("1.8.6"),
    V1_8_7("1.8.7"),
    V1_8_8("1.8.8"),
    V1_8_9("1.8.9"),
    V1_9("1.9"),
    V1_9_1("1.9.1"),
    V1_9_2("1.9.2"),
    V1_9_3("1.9.3"),
    V1_9_4("1.9.4"),
    V1_10("1.10"),
    V1_10_1("1.10.1"),
    V1_10_2("1.10.2"),
    V1_11("1.11"),
    V1_11_1("1.11.1"),
    V1_11_2("1.11.2"),
    V1_12("1.12"),
    V1_12_1("1.12.1"),
    V1_12_2("1.12.2"),
    V1_13("1.13"),
    V1_13_1("1.13.1"),
    V1_13_2("1.13.2"),
    V1_14("1.14"),
    V1_14_1("1.14.1"),
    V1_14_2("1.14.2"),
    V1_14_3("1.14.3"),
    V1_14_4("1.14.4"),
    V1_15("1.15"),
    V1_15_1("1.15.1"),
    V1_15_2("1.15.2"),
    V1_16("1.16"),
    V1_16_1("1.16.1"),
    V1_16_2("1.16.2"),
    V1_16_3("1.16.3"),
    V1_16_4("1.16.4"),
    V1_16_5("1.15.5"),
    V1_17("1.17"),
    V1_17_1("1.17.1"),
    V1_18("1.18"),
    V1_18_1("1.18.1"),
    V1_18_2("1.18.2"),
    V1_19("1.19"),
    V1_19_1("1.19.1"),
    V1_19_2("1.19.2"),
    V1_19_3("1.19.3");

    val version: Version = DefaultVersion.parse(versionString)

    override fun asString(): String = version.asString()

    override fun toString(): String = asString()

    companion object {
        /**
         * The latest released [MinecraftVersion].
         */
        @JvmStatic
        val LATEST = V1_19_3

        private val VALUES = setOf(*values())
        private val MAP_BY_STRINGS = VALUES.associateBy(MinecraftVersion::versionString)

        /**
         * Finds a [MinecraftVersion] matching the [version] string.
         *
         * @return the founded version, `null` if not found.
         */
        fun find(version: String) = MAP_BY_STRINGS[version]

        /**
         * Gets a [MinecraftVersion] matching the [version] string, throw an [IllegalStateException] if not
         * found.
         */
        fun matching(version: String) =
            checkNotNull(find(version)) { "Couldn't found any MinecraftVersion match '$version'!" }

        /**
         * Gets a [MinecraftVersion] from [version] instance, throw an [IllegalStateException] if not found.
         */
        fun matching(version: Version) = version as? MinecraftVersion ?: matching(version.asString())
    }
}
