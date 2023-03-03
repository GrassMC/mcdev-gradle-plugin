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
import org.gradle.kotlin.dsl.maven

/**
 * This class hold the [name] and the [url] of Maven repository.
 */
internal data class RepositoryHolder(val name: String, val url: String) {
    fun configureIfNotExist(handler: RepositoryHandler) = handler.findByName(name) ?: configure(handler)

    fun configure(handler: RepositoryHandler, action: MavenArtifactRepository.() -> Unit = {}) =
        handler.maven(url) {
            name = this@RepositoryHolder.name
            action()
        }
}

/**
 * This class hold the dependency data.
 *
 * If [snapshot] is true, "-SNAPSHOT" will add the suffix.
 *
 * The order of dependency version suffix is: [versionSuffix]"-SNAPSHOT"
 */
internal data class DependencyHolder(
    val group: String,
    val artifact: String,
    val snapshot: Boolean = false,
    val versionSuffix: String? = null,
) {
    /**
     * Gets the dependency notation which the given [version] and the override [snapshot] version.
     *
     * Example: `io.github.grassmc.mcdev:mcdev-gradle-plugin:1.0.0`
     */
    fun notation(version: String, snapshot: Boolean? = null): String {
        val suffix = buildString {
            versionSuffix?.also(::append)
            if (snapshot ?: this@DependencyHolder.snapshot) append(SNAPSHOT_SUFFIX)
        }
        return "$group:$artifact:${version}$suffix"
    }

    private companion object {
        const val SNAPSHOT_SUFFIX = "-SNAPSHOT"
    }
}
