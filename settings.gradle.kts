import org.gradle.api.initialization.resolve.RepositoriesMode

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

rootProject.name = "WoWSFT"

include("wowsft-core")
include("WoWSFT-Shared")
include("WoWSFT-Data")
include("WoWSFT-App")

project(":wowsft-core").projectDir = file("wowwft-core")
