plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":kmt-common"))
    implementation(project(":kmt-vector"))
    implementation(project(":kmt-interval"))
    implementation(project(":kmt-trigonometry"))
    implementation("org.jetbrains.kotlinx:multik-core:0.2.1")
    implementation("org.jetbrains.kotlinx:multik-kotlin:0.2.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
