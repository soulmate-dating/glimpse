pluginManagement {
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

rootProject.name = "glimpse"
include(":app")

val sourcesPath = "./sources/"

include(":common-navigation")
project(":common-navigation").projectDir = file("${sourcesPath}common-navigation")
include(":common-coroutines")
project(":common-coroutines").projectDir = file("${sourcesPath}common-coroutines")
