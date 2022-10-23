plugins {
    kotlin("jvm")
    `java-library`
}

group = findProperty("kmt.group") ?: "com.github.polyrocketmatt"
version = findProperty("kmt.version") ?: "0.0.1"

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
    implementation(project(":kmt-trigonometry"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}