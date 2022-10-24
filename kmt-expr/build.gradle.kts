plugins {
    kotlin("jvm")
    `java-library`
}

description = "Module for working with symbolic expressions"

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
