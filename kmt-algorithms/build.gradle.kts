plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
    implementation(project(":kmt-interval"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
