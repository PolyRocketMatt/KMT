plugins {
    kotlin("jvm")
    `java-library`
}

description = "Module that contains core concepts of linear algebra"

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
