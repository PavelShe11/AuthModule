

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

val moduleBaseName = findProperty("path")
    .toString()
    .replace(":", ".")
    .removePrefix(".")

val moduleGroup = "${findProperty("group")}.$moduleBaseName"

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}

android {
    namespace = moduleGroup
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = property("group").toString() + ".sample"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdkPreview = libs.versions.android.targetSdk.get()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    //Project dependencies
    implementation(projects.sampleApp.umbrella)

    //Network (ktor)
    implementation(libs.ktor.client.okhttp)

    //Platform SDK + Jetpack
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.foundation.layout.android)

    //Di (koin)
    implementation(libs.koin.android)

    //coroutine
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.foundation.android)
}