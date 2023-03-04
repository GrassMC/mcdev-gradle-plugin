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

import io.github.grassmc.mcdev.gradle.ServerVendor.PaperMC
import io.github.grassmc.mcdev.gradle.ServerVendor.SpigotMC
import io.github.grassmc.mcdev.gradle.extensions.CommonRepositories
import io.github.grassmc.mcdev.gradle.extensions.MinecraftRepositories
import io.github.grassmc.mcdev.gradle.version.MinecraftVersion
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class McdevSpigotPluginTest {
    private lateinit var tester: PlatformPluginTester

    @BeforeEach
    fun setUp() {
        tester = PlatformPluginTester(SpigotMC)
    }

    @Test
    fun `plugin register extensions`() = tester.testRegister()

    @Test
    fun `plugin project extension with default values`() = tester.testExtension(MinecraftVersion.LATEST)

    @Test
    fun `plugin with default repositories`() =
        tester.testRepositories(CommonRepositories.SONATYPE.name, MinecraftRepositories.SPIGOT_MC.name)

    @Test
    fun `plugin with default dependencies`() = tester.testDependencies(MinecraftVersion.LATEST)
}

class McdevPaperPluginTest {
    private lateinit var tester: PlatformPluginTester

    @BeforeEach
    fun setUp() {
        tester = PlatformPluginTester(PaperMC)
    }

    @Test
    fun `plugin register extensions`() = tester.testRegister()

    @Test
    fun `plugin project extension with default values`() = tester.testExtension(MinecraftVersion.LATEST)

    @Test
    fun `plugin with default repositories`() = tester.testRepositories(MinecraftRepositories.PAPER_MC.name)

    @Test
    fun `plugin with default dependencies`() = tester.testDependencies(MinecraftVersion.LATEST)
}
