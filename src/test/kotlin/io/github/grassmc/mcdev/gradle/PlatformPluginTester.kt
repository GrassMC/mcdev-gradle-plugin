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

import io.github.grassmc.mcdev.gradle.version.Version
import org.gradle.kotlin.dsl.getByName
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class PlatformPluginTester(platformVendor: PlatformVendor) {
    private val pluginId = "io.github.grassmc.mcdev.${platformVendor.displayName.lowercase()}"

    private fun createProject() = ProjectBuilder.builder().build().also { it.plugins.apply(pluginId) }

    fun testRegister() {
        val project = createProject()

        assertNotNull(project.extensions.findByName(McdevPlatformPluginBase.EXTENSION_NAME))
    }

    fun testExtension(expectedVendor: ServerVendor, expectedVersion: Version) {
        val project = createProject()
        val extension = project.extensions.getByName<McdevProjectExtension>(McdevPlatformPluginBase.EXTENSION_NAME)

        assertEquals(expectedVendor, extension.apiVendor)
        assertEquals(expectedVersion, extension.apiVersion)
    }

    fun testRepositories(vararg expectedRepos: String) {
        val project = createProject()
        val repos = project.repositories.names

        expectedRepos.forEach { assertContains(repos, it) }
    }

    fun testDependencies(expectedDep: String) {
        val project = createProject()

        val configuration = project.configurations.findByName(McdevPlatformPluginBase.MCDEV_CONFIGURATION_NAME)
        assertNotNull(configuration)

        project.afterEvaluate {
            val depNotations = configuration.dependencies.map { "${it.group}:${it.name}:${it.version}" }
            assertContains(depNotations, expectedDep)
        }
    }
}
