plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":recipe-app-api-v1-jackson"))
    implementation(project(":ok-marketplace-common"))

    testImplementation(kotlin("test-junit"))
}