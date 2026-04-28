repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly(project(":api-definition"))
    compileOnly("org.spigotmc:spigot-api:1.21.11-R0.1-SNAPSHOT")
}

mavenPublishing {
    coordinates("com.pixelatedslice.easyconfig", "serializers-bukkit", version.toString())

    pom {
        name.set("EasyConfig - Serializers for Bukkit")
        description.set("EasyConfig's Serializers for Bukkit.")
    }
}
