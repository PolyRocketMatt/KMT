plugins {
    kotlin("jvm")
    `java-library`
}

description = "Module for faster trigonometric functions"

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
