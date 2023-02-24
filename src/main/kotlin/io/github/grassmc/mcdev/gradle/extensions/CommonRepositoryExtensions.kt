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

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository

private object CommonRepositories {
    val SONATYPE = RepositoryHolder(
        "Sonatype OSS Snapshots", "https://oss.sonatype.org/content/repositories/snapshots/",
    )
    val SONATYPE_S01 = RepositoryHolder(
        "Sonatype S01 OSS Snapshots", "https://s01.oss.sonatype.org/content/repositories/snapshots/",
    )
    val JITPACK = RepositoryHolder("Jitpack", "https://jitpack.io/")
}

/**
 * Adds a repository which looks Sonatype Snapshots Maven repository for dependencies.
 *
 * The URL used to access this repository is [here](https://oss.sonatype.org/content/repositories/snapshots/).
 *
 * @param action a configuration action.
 * @return The added repository.
 */
fun RepositoryHandler.sonatype(action: MavenArtifactRepository.() -> Unit = {}) =
    CommonRepositories.SONATYPE.configure(this, action)

/**
 * Adds a repository which looks S01 Sonatype Snapshots Maven repository for dependencies.
 *
 * The URL used to access this repository is [here](https://s01.oss.sonatype.org/content/repositories/snapshots/).
 *
 * @param action a configuration action.
 * @return The added repository.
 */
fun RepositoryHandler.sonatypeS01(action: MavenArtifactRepository.() -> Unit = {}) =
    CommonRepositories.SONATYPE_S01.configure(this, action)

/**
 * Adds a repository which looks Jitpack Maven repository for dependencies.
 *
 * The URL used to access this repository is [here](https://jitpack.io/).
 *
 * @param action a configuration action.
 * @return The added repository.
 */
fun RepositoryHandler.jitpack(action: MavenArtifactRepository.() -> Unit) =
    CommonRepositories.JITPACK.configure(this, action)
