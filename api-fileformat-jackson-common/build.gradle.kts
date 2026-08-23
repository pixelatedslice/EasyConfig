dependencies {
    implementation(project(":api-definition"))
    implementation(project(":api-implementation"))
    implementation(libs.jackson.core)
    implementation(libs.jackson.databind)
}

mavenPublishing {
    coordinates("com.pixelatedslice.easyconfig", "fileformat-common", version.toString())

    pom {
        name.set("EasyConfig - Shared API for all File Formats")
        description.set("EasyConfig's shared API for all File Formats.")
    }
}