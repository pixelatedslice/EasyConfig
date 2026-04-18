version = "1.1.0"

dependencies {
    compileOnly(project(":api-definition"))
    api(libs.jackson.core)
}