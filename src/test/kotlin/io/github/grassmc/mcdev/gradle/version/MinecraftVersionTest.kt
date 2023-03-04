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

import io.github.grassmc.mcdev.gradle.version.MinecraftVersion.V1_13_2
import io.github.grassmc.mcdev.gradle.version.MinecraftVersion.V1_19_3
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertSame

class MinecraftVersionTest {
    @Test
    fun `find Minecraft versions`() {
        assertNotNull(MinecraftVersion.find("1.19.3"))
        assertNull(MinecraftVersion.find("1.7"))
        assertNull(MinecraftVersion.find("1.0.0-SNAPSHOT"))
        assertSame(V1_13_2, MinecraftVersion.find("1.13.2"))
    }

    @Test
    fun `matching Minecraft versions`() {
        assertDoesNotThrow { MinecraftVersion.matching("1.12.2") }
        assertThrows<IllegalStateException> { MinecraftVersion.matching("latest") }
        assertThrows<IllegalStateException> { MinecraftVersion.matching(Version.UNKNOWN) }
        assertSame(V1_19_3, MinecraftVersion.matching("1.19.3"))
        assertSame(V1_19_3, MinecraftVersion.matching(Version.from("1.19.3")))
    }
}
