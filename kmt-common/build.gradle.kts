plugins {
    kotlin("jvm")
    `java-library`
}

description = "Module for common utilities shared between KMT modules"

dependencies {
    testImplementation(kotlin("test"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
