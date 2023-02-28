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
import io.github.grassmc.mcdev.gradle.extensions.paperMC
import io.github.grassmc.mcdev.gradle.extensions.purpurMC
import io.github.grassmc.mcdev.gradle.extensions.sonatype
import io.github.grassmc.mcdev.gradle.extensions.spigotMC
import io.github.grassmc.mcdev.gradle.version.Version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.internal.tasks.JvmConstants
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get

internal abstract class AbstractPlatformPlugin(private val platformType: PlatformType) : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        plugins.apply(McdevBasePlugin::class)

        configurePlatformDevEnv()
    }

    private fun Project.configurePlatformDevEnv() =
        extensions.create("${platformType.identifier}Dev", PlatformDevExtension::class).run {
            repositories.setupPlatformApiVendorRepo(apiVendor)
            setupPlatformVendorDependency(apiVendor, apiVersion)
        }

    private fun RepositoryHandler.setupPlatformApiVendorRepo(vendor: PlatformVendor) {
        when (vendor) {
            SpigotMC -> findByName(MinecraftRepositories.SPIGOT_MC.name) ?: spigotMC()
            PurpurMC -> findByName(MinecraftRepositories.PURPUR_MC.name) ?: purpurMC()
            BungeeCord -> findByName(CommonRepositories.SONATYPE.name) ?: sonatype()
            PaperMC, Velocity, Waterfall -> findByName(MinecraftRepositories.PAPER_MC.name) ?: paperMC()
        }
    }

    private fun Project.setupPlatformVendorDependency(vendor: PlatformVendor, version: Version) {
        val platformApiConfiguration = configurations.create("platformApi")
        dependencies.add(platformApiConfiguration.name, vendor.apiNotation(version))

        platformVendorArtifactConfiguraionNames.forEach {
            configurations[it].extendsFrom(platformApiConfiguration)
        }
    }
}

private val platformVendorArtifactConfiguraionNames =
    listOf(JvmConstants.COMPILE_ONLY_CONFIGURATION_NAME, JvmConstants.TEST_IMPLEMENTATION_CONFIGURATION_NAME)
