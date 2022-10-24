plugins {
    kotlin("jvm")
    `java-library`
}

description = "Module for NxM-matrices"

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
    implementation(project(":kmt-trigonometry"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
