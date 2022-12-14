rootProject.name = "KMT"
include("kmt-common")
include("kmt-interval")
include("kmt-trigonometry")
include("kmt-vector")
include("kmt-function")
include("kmt-matrix")
include("kmt-expr")
include("kmt-linalg")
include("kmt-group")
include("kmt-complex")

plugins {
    id("com.gradle.enterprise") version("3.9")
}

gradleEnterprise {
    if (System.getenv("CI") != null) {
        buildScan {
            publishAlways()
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
        }
    }
}
