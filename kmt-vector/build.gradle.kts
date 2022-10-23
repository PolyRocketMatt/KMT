plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
    implementation(project(":kmt-matrix"))
    implementation(project(":kmt-trigonometry"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
