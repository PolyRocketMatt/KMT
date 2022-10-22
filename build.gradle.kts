val rootFolder = rootProject.projectDir
val mergedJar by configurations.creating<Configuration> {
    isCanBeConsumed = false
    isCanBeResolved = true
    isVisible = false
}

group = findProperty("kmt.group") ?: "com.github.polyrocketmatt"
version = findProperty("kmt.version") ?: "0.0.1"

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
    configure<PublishingExtension> {
        publications {
            repositories {
                maven {
                    name = "GitHubPackages"
                    url = uri("https://maven.pkg.github.com/polyrocketmatt/kmt")
                    credentials {
                        username = System.getenv("GITHUB_USER")
                        password = System.getenv("GITHUB_TOKEN")
                    }
                }
            }

            create<MavenPublication>("maven") {
                groupId = group as String
                version = version as String
                artifactId = project.name.toLowerCase()
            }
        }
    }
}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(rootFolder.resolve("dokka"))
}

dependencies {
    mergedJar(project(":kmt-algorithms"))
    mergedJar(project(":kmt-common"))
    mergedJar(project(":kmt-function"))
    mergedJar(project(":kmt-interval"))
    mergedJar(project(":kmt-matrix"))
    mergedJar(project(":kmt-trigonometry"))
    mergedJar(project(":kmt-vector"))
}

tasks.register<Exec>("deploy") {
    dependsOn(tasks.jar)

    val deployGroup = findProperty("kmt.group") as String
    val deployVersion = findProperty("kmt.version") as String

    println("Deploying to PolyRocketMatt's Warehouse")
    println("Group: $deployGroup")
    println("Version: $deployVersion")
    commandLine("cmd",
        "/c",
        "${rootFolder}\\deploy.bat",
        deployGroup.replace(".", "\\"),
        "KMT",
        deployVersion
    )
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
