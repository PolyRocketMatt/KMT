plugins {
    kotlin("jvm")
    `java-library`
}

description = "Module for working with mathematical functions"

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
    implementation(project(":kmt-matrix"))
    implementation(project(":kmt-vector"))
    implementation(project(":kmt-interval"))
    implementation(project(":kmt-trigonometry"))
    implementation("org.jetbrains.kotlinx:multik-core:0.2.1")
    implementation("org.jetbrains.kotlinx:multik-kotlin:0.2.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
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
            artifactId = "kmt-function"
            version = findProperty("kmt.version") as String? ?: "0.0.1"

            from(components["java"])

            pom {
                name.set("KMT Function")
                description.set("Module for working with mathematical functions")
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
