plugins {
    kotlin("jvm")
    `java-library`
}

group = "com.github.polyrocketmatt"
version = "0.0.2"

dependencies {
    testImplementation(kotlin("test"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
