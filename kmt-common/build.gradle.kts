plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
