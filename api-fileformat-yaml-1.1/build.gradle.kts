version = "1.0.0"

dependencies {
    compileOnly(project(":api-definition"))
    implementation(project(":api-fileformat-jackson-common"))
    implementation(libs.jackson.yaml)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platformLauncher)
}