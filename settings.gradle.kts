pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://jitpack.io")
        }  //Make sure to add this in your project for uCrop - an internal library
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }  //Make sure to add this in your project for uCrop - an internal library

    }
}

rootProject.name = "Mawruth"
include(":app")
include(":data")
include(":domain")
