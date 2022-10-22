val rootFolder = rootProject.projectDir
val mergedJar by configurations.creating<Configuration> {
    isCanBeConsumed = false
    isCanBeResolved = true
    isVisible = false
}

group = findProperty("kmt.group") ?: "com.github.polyrocketmatt"
version = findProperty("kmt.version") ?: "0.0.1"

dependencies {
    mergedJar(project(":kmt-algorithms"))
    mergedJar(project(":kmt-common"))
    mergedJar(project(":kmt-function"))
    mergedJar(project(":kmt-interval"))
    mergedJar(project(":kmt-matrix"))
    mergedJar(project(":kmt-trigonometry"))
    mergedJar(project(":kmt-vector"))
    implementation(kotlin("stdlib-jdk8"))
}

allprojects {
    group = "com.github.polyrocketmatt"
    version = findProperty("kmt.version") ?: "0.0.1"

    repositories {
        mavenCentral()
    }
}

plugins {
    id("maven-publish")
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.dokka") version "1.7.10"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "maven-publish")

}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(rootFolder.resolve("dokka"))
}

tasks.jar {
    dependsOn(mergedJar)
    from({
        mergedJar
            .filter { it.name.endsWith("jar") && it.path.contains(rootDir.path) }
            .map {
                logger.lifecycle("depending on $it")
                zipTree(it)
            }
    })
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/polyrocketmatt/KMT")
            credentials {
                username = "PolyRocketMatt"
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.polyrocketmatt"
            artifactId = "kmt"
            version = findProperty("kmt.version") as String? ?: "0.0.1"

            from(components["java"])
            //artifact(tasks["jar"])

            pom {
                name.set("Kotlin Mathematical Toolkit")
                description.set("A collection of math utilities for Kotlin")
                url.set("https://github.com/PolyRocketMatt/KMT")

                licenses {
                    license {
                        name.set("GNU General Public License v3.0")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.html")
                    }
                }

                developers {
                    developer {
                        id.set("PolyRocketMatt")
                        name.set("Matthias Kovacic")
                        email.set("matthias.kovacic@gmail.com")
                    }
                }
            }
        }
    }
}
