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

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository

internal object MinecraftRepositories {
    // Platform
    val SPIGOT_MC = RepositoryHolder(
        "SpigotMC Repository", "https://hub.spigotmc.org/nexus/content/repositories/snapshots",
    )
    val PAPER_MC = RepositoryHolder("PaperMC Repository", "https://repo.papermc.io/repository/maven-snapshots/")
    val PURPUR_MC = RepositoryHolder("PurpurMC Repository", "https://repo.purpurmc.org/snapshots/")

    // CodeMC
    val CODE_MC = RepositoryHolder("CodeMC Repository", "https://repo.codemc.io/repository/maven-public/")
    val CODE_MC_NMS = RepositoryHolder("CodeMC NMS Repository", "https://repo.codemc.io/repository/nms/")
}

/**
 * Adds a repository which looks SpigotMC Maven repository for `spigot-api` dependencies.
 *
 * The URL used to access this repository is [here](https://hub.spigotmc.org/nexus/content/repositories/snapshots/).
 *
 * @param action a configuration action.
 * @return The added repository.
 */
fun RepositoryHandler.spigotMC(action: MavenArtifactRepository.() -> Unit = {}) =
    MinecraftRepositories.SPIGOT_MC.configure(this, action)

/**
 * Adds a repository which looks PaperMC Maven repository for `paper-api`, `waterfall-api`, `velocity-api` dependencies.
 *
 * The URL used to access this repository is [here](https://repo.papermc.io/repository/maven-snapshots/).
 *
 * @param action a configuration action.
 * @return The added repository.
 */
fun RepositoryHandler.paperMC(action: MavenArtifactRepository.() -> Unit = {}) =
    MinecraftRepositories.PAPER_MC.configure(this, action)

/**
 * Adds a repository which looks Purpur Maven repository for `purpur-api` dependencies.
 *
 * The URL used to access this repository is [here](https://repo.purpurmc.org/snapshots/).
 *
 * @param action a configuration action.
 * @return The added repository.
 */
fun RepositoryHandler.purpurMC(action: MavenArtifactRepository.() -> Unit = {}) =
    MinecraftRepositories.PURPUR_MC.configure(this, action)

/**
 * Adds a repository which looks CodeMC Maven repository for dependencies.
 *
 * The URL used to access this repository is [here](https://repo.codemc.io/repository/maven-public/).
 *
 * @param action a configuration action.
 * @return The added repository.
 */
fun RepositoryHandler.codeMC(action: MavenArtifactRepository.() -> Unit = {}) =
    MinecraftRepositories.CODE_MC.configure(this, action)

/**
 * Adds a repository which looks CodeMC NMS Maven repository for `net.minecraft.server` dependencies.
 *
 * The URL used to access this repository is [here](https://repo.codemc.io/repository/nms/).
 *
 * @param action a configuration action.
 * @return The added repository.
 */
fun RepositoryHandler.nms(action: MavenArtifactRepository.() -> Unit = {}) =
    MinecraftRepositories.CODE_MC_NMS.configure(this, action)
