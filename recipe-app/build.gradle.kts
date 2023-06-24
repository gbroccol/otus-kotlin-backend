plugins {
    kotlin("jvm")
}

allprojects {
    group = "org.otus"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        google()
        maven{ url = uri("https://jitpack.io")}
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}
