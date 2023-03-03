import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun property(name: String) = project.findProperty(name)?.toString()

plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "1.1.0"
    signing
}

group = property("group")!!
version = property("version")!!
description = property("description")

val jdkVersion = "17"
kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(jdkVersion))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest(embeddedKotlinVersion)
        }

        val functionalTest by registering(JvmTestSuite::class) {
            useKotlinTest(embeddedKotlinVersion)

            dependencies {
                implementation(project())
            }

            targets {
                all {
                    testTask.configure { shouldRunAfter(test) }
                }
            }
        }
    }
}

gradlePlugin {
    testSourceSets(sourceSets["functionalTest"])
    plugins {
        val prefix = "io.github.grassmc.mcdev"
        listOf("spigot").forEach {
            create(it) {
                id = "$prefix.$it"
                implementationClass = "$prefix.gradle.Mcdev{${it.capitalized()}Plugin}"
            }
        }
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = jdkVersion
        targetCompatibility = jdkVersion
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = jdkVersion
    }

    named("check") {
        dependsOn(testing.suites.named("functionalTest"))
    }
}
