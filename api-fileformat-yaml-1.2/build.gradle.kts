version = "1.0.0"

dependencies {
    compileOnly(project(":api-definition"))
    compileOnly(project(":api-implementation"))
    implementation(project(":api-fileformat-jackson-common"))
    implementation(libs.jackson.yaml)
}