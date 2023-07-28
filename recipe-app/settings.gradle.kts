
rootProject.name = "recipe-app"

pluginManagement {
//    val kotlinVersion: String by settings
//    val openapiVersion: String by settings

    val kotlinVersion: String by settings
    val openapiVersion: String by settings
    val springframeworkBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val pluginSpringVersion: String by settings
    val pluginJpa: String by settings
//    val ktorVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion apply false

        kotlin("plugin.serialization") version kotlinVersion apply false

        id("org.springframework.boot") version springframeworkBootVersion apply false
        id("io.spring.dependency-management") version springDependencyManagementVersion apply false
        kotlin("plugin.spring") version pluginSpringVersion apply false
        kotlin("plugin.jpa") version pluginJpa apply false

//        id("io.ktor.plugin") version ktorVersion apply false

        id("org.openapi.generator") version openapiVersion apply false
    }

//    plugins {
//        kotlin("jvm") version kotlinVersion apply false
//
//        kotlin("plugin.serialization") version kotlinVersion apply false
//
//        id("org.openapi.generator") version openapiVersion apply false
//    }
}

//include("m1l1-hello")

include("recipe-app-api-v1-jackson")
//include("recipe-app-api-v1-kmp")

/* внутренные модели */
include("ok-marketplace-common")

/* мапперы */
include("ok-marketplace-mappers-v1")
//include("ok-marketplace-mappers-v2")

include("ok-marketplace-app-spring")
include("ok-marketplace-biz")           // бизнес-логика
include("ok-marketplace-stubs")         // заглушки
