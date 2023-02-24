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

interface PlatformVendor {
    val displayName: String
}

enum class ServerVendor : PlatformVendor {
    Spigot,
    Paper,
    Purpur,
    Krypton;

    override val displayName: String
        get() = this.name
}

enum class ProxyVendor(displayName: String? = null) : PlatformVendor {
    BungeeCord("Bungee"),
    Waterfall,
    Velocity;

    override val displayName: String = displayName ?: this.name
}
