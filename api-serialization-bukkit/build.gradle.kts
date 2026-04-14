version = "1.0.0"

repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly(project(":api-definition"))
    compileOnly("org.spigotmc:spigot-api:1.21.11-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}