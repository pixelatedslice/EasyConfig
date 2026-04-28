import com.vanniktech.maven.publish.JavaPlatform

dependencies {
    constraints {
        api(project(":api-definition"))
        api(project(":api-fileformat-json"))
        api(project(":api-fileformat-toml"))
        api(project(":api-fileformat-yaml-1.2"))
        api(project(":api-implementation"))

        // External dependencies
        api(libs.guava)  // exposed via api-definition
    }
}

mavenPublishing {
    configure(JavaPlatform())
    coordinates("com.pixelatedslice.easyconfig", "bom", version.toString())

    pom {
        name.set("EasyConfig - Bill of Material")
        description.set("EasyConfig's BOM")
    }
}

javaPlatform {
    allowDependencies()
}