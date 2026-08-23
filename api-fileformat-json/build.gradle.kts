dependencies {
    compileOnly(project(":api-definition"))
    compileOnly(project(":api-implementation"))
    implementation(project(":api-fileformat-jackson-common"))
    implementation(libs.jackson.core)
    implementation(libs.jackson.databind)
}

mavenPublishing {
    coordinates("com.pixelatedslice.easyconfig", "fileformat-json", version.toString())

    pom {
        name.set("EasyConfig - Core")
        description.set("EasyConfig's official JSON File Format Provider using Jackson")
    }
}
