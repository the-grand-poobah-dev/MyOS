pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://www.jitpack.io") }
    }
}

rootProject.name = "MyOS"
include(":app")
include(":launcher")
project(":launcher").projectDir = file("sources/Launcher-main/app")
include(":phone")
project(":phone").projectDir = file("sources/Phone-main/app")
include(":messages")
project(":messages").projectDir = file("sources/Messages-main/app")
