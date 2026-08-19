dependencies {
    api(libs.guava)
    compileOnly(libs.jetbrains.annotations)
}

mavenPublishing {
    coordinates("com.pixelatedslice.easyconfig", "api", version.toString())

    pom {
        name.set("EasyConfig - Core")
        description.set("EasyConfig's API Interfaces only")
    }
}