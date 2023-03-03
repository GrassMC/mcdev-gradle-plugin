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

import io.github.grassmc.mcdev.gradle.ProxyVendor.*
import io.github.grassmc.mcdev.gradle.ServerVendor.*
import io.github.grassmc.mcdev.gradle.extensions.CommonRepositories
import io.github.grassmc.mcdev.gradle.extensions.MinecraftRepositories
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.internal.tasks.JvmConstants
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get

abstract class McdevPlatformPluginBase(protected val platformName: String) : Plugin<Project> {
    protected abstract fun configDefaultProjectExtension(extension: McdevProjectExtension)

    override fun apply(project: Project): Unit = project.run {
        applyPlugin<JavaPlugin>()

        val extension = extensions.create<McdevProjectExtension>(EXTENSION_NAME)
            .also { configDefaultProjectExtension(it) }
        setupDependency(extension)
    }

    private fun RepositoryHandler.setupRepositories(vendor: PlatformVendor) {
        when (vendor) {
            PurpurMC -> MinecraftRepositories.PURPUR_MC.configureIfNotExist(this)
            BungeeCord -> CommonRepositories.SONATYPE.configureIfNotExist(this)
            PaperMC, Velocity, Waterfall -> MinecraftRepositories.PAPER_MC.configureIfNotExist(this)
            SpigotMC -> {
                CommonRepositories.SONATYPE.configureIfNotExist(this)
                MinecraftRepositories.SPIGOT_MC.configureIfNotExist(this)
            }
        }
    }

    private fun Project.setupDependency(extension: McdevProjectExtension) {
        configurations.create(MCDEV_CONFIGURATION_NAME) {
            platformVendorArtifactConfigurationNames.forEach {
                configurations[it].extendsFrom(this)
            }
        }
        afterEvaluate {
            repositories.setupRepositories(extension.apiVendor)
            dependencies.add(MCDEV_CONFIGURATION_NAME, extension.apiVendor.dependencyNotation(extension.apiVersion))
        }
    }

    companion object {
        const val EXTENSION_NAME = "mcdev"
        const val MCDEV_CONFIGURATION_NAME = "mcdev"

        inline fun <reified T : Plugin<*>> Project.applyPlugin() = pluginManager.apply(T::class.java)
    }
}

private val platformVendorArtifactConfigurationNames =
    listOf(JvmConstants.COMPILE_ONLY_CONFIGURATION_NAME, JvmConstants.TEST_IMPLEMENTATION_CONFIGURATION_NAME)
