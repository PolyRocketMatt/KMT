plugins {
    kotlin("jvm")
    `java-library`
}

group = "com.github.polyrocketmatt"
version = "0.0.1"

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
    implementation(project(":kmt-interval"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
