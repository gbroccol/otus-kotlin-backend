
rootProject.name = "recipe-app"
include("m01-01-hello-world")

pluginManagement{

    val kotlinVersion: String by settings
    plugins{
        kotlin("jvm") version kotlinVersion apply false
    }
}
