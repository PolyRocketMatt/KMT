plugins {
    kotlin("jvm")
    `java-library`
}

description = "Module for 1-dimensional matrices (or vectors)"

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
    implementation(project(":kmt-group"))
    implementation(project(":kmt-matrix"))
    implementation(project(":kmt-trigonometry"))
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
            artifactId = "kmt-vector"
            version = findProperty("kmt.version") as String? ?: "0.0.1"

            from(components["java"])

            pom {
                name.set("KMT Vector")
                description.set("Module for 1-dimensional matrices (or vectors)")
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
