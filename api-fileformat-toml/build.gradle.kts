version = "1.0.0"

dependencies {
    compileOnly(project(":api-definition"))
    implementation(project(":api-fileformat-jackson-common"))
    implementation(libs.jackson.toml)
}