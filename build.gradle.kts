import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Locale

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
    id("org.gradle.crypto.checksum") version "1.4.0"
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
    implementation(kotlin("stdlib-jdk8"))
}

tasks.register<Exec>("versioning") {
    val isWindows = System.getenv("os.name").toLowerCase(Locale.ROOT).contains("windows")
    if (isWindows)
        commandLine("cmd", "/c", "version.bat", version as String)
}

tasks.jar {
    dependsOn("versioning")
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
repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
