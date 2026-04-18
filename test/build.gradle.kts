version = "1.0.0"

repositories {
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

dependencies {
    testImplementation(project(":api-definition"))
    testImplementation(project(":api-implementation"))
    testImplementation(project(":api-fileformat-yaml-1.2"))
    testImplementation(project(":api-fileformat-json"))
    testImplementation(project(":api-fileformat-toml"))
    testImplementation(project(":api-fileformat-toml"))
    testImplementation(project(":api-serialization-bukkit"))
    testImplementation("org.spigotmc:spigot-api:1.21.11-R0.1-SNAPSHOT")
}