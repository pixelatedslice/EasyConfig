plugins {
    `java-library`
}

subprojects {
    apply(plugin = "java-library")

    group = "com.pixelatedslice.easyconfig"

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("com.google.auto.service:auto-service-annotations:1.1.1")
        annotationProcessor("com.google.auto.service:auto-service:1.1.1")
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.test {
        useJUnitPlatform()
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
    }
}