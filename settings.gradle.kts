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
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DashNote"
include(":app")
include(":core:common")
include(":core:designsystem")
include(":core:database")
include(":core:navigation")

include(":data:auth")

include(":feature:auth:api")
include(":feature:auth:impl")
include(":feature:notes:api")
include(":feature:notes:impl")
include(":feature:settings:api")
include(":feature:settings:impl")
