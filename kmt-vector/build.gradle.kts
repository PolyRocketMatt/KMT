plugins {
    kotlin("jvm")
    `java-library`
}

description = "Module for 1-dimensional matrices (or vectors)"

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
    implementation(project(":kmt-matrix"))
    implementation(project(":kmt-trigonometry"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
