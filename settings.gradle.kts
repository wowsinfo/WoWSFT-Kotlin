rootProject.name = "WoWSFT"
//rootProject.version = "0.10.10.0-1"
//rootProject.group = "WoWSFT"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":WoWSFT-Shared")
include(":WoWSFT-Data")
include(":WoWSFT-App")
