plugins {
    kotlin("jvm")
    `java-library`
}

group = "com.github.polyrocketmatt"
version = "0.0.1"

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":common"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}