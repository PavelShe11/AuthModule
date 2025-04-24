rootProject.name = "AuthModule"

pluginManagement {
    repositories {
        google {
            content { 
              	includeGroupByRegex("com\\.android.*")
              	includeGroupByRegex("com\\.google.*")
              	includeGroupByRegex("androidx.*")
              	includeGroupByRegex("android.*")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

dependencyResolutionManagement {
    repositories {
        google {
            content {
              	includeGroupByRegex("com\\.android.*")
              	includeGroupByRegex("com\\.google.*")
              	includeGroupByRegex("androidx.*")
              	includeGroupByRegex("android.*")
            }
        }
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

includeBuild("convention-plugins")

include(":uikit")
include(":common")

include(":mechanismAuth:core")
include(":mechanismAuth:oauth_2_0:core")
include(":mechanismAuth:oauth_2_0:impl")
include(":mechanismAuth:oauth_2_0:ktor")

include(":featureAuth:core")
include(":featureAuth:presentation")
include(":featureAuth:ui")

include(":sampleApp:umbrella")
include(":sampleApp:androidApp")
