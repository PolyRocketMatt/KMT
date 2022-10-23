plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}