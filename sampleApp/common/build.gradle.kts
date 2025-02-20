import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp)
}

group = "io.github.pavelshe11.auth_module.sampleApp.common"
version = "0.1"

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "SampleApp.Common"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        commonMain.dependencies {
            //Project dependencies
            implementation(project(":oauth_2_0:impl"))
            implementation(project(":oauth_2_0:ktor"))

            implementation(project(":sampleApp:uikit"))

            //UI (compose)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            //Network (ktor)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)

            //DI (koin)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(project.dependencies.platform(libs.koin.annotations.bom))
            api(libs.koin.annotations)

            //mvikotlin
            implementation(libs.mvikotlin)
            implementation(libs.mvikotlin.main)
            implementation(libs.mvikotlin.logging)
            implementation(libs.mvikotlin.extensions.coroutines)

            //Navigation (decompose)
            implementation(libs.decompose)
            implementation(libs.decompose.compose)

            //essenty
            implementation(libs.essenty.lifecycle.coroutines)
        }

        val desktopMain by getting

        androidMain.dependencies {
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
        }

        desktopMain.dependencies {
            //UI (compose)
            implementation(compose.desktop.currentOs)

            //coroutine
            implementation(libs.kotlinx.coroutines.swing)
        }
    }

    sourceSets.named("commonMain").configure {
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }
}

android {
    namespace = "io.github.pavelshe11.auth_module.sampleApp.common"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

compose.resources {
    publicResClass = false
    generateResClass = always
}