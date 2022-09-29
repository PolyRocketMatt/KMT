plugins {
    kotlin("jvm")
    `java-library`
}

group = "com.github.polyrocketmatt"
version = "1.0-SNAPSHOT"

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}