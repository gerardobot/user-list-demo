@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("config")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "UserList"

include(
    ":app",
    ":domain",
    ":data",
    ":framework"
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
 