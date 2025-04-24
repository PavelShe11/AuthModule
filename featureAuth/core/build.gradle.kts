plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.serialization)
}

val moduleBaseName = findProperty("path")
    .toString()
    .replace(":", ".")
    .removePrefix(".")

val moduleGroup = "${findProperty("group")}.$moduleBaseName"

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = moduleBaseName
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.mechanismAuth.oauth20.core)

            implementation(projects.common)

            //mvikotlin
            api(libs.mvikotlin)
            implementation(libs.mvikotlin.extensions.coroutines)

            //coroutines
            implementation(libs.kotlinx.coroutines.core)

            //serialization
            implementation(libs.kotlinx.serialization.json)
        }
    }
}

android {
    namespace = moduleGroup
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}