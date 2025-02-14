plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

group = property("group").toString() + ".uikit"

kotlin {

    jvmToolchain(11)
    androidTarget {
        publishLibraryVariants("release")
        withSourcesJar(publish = true)
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "uikit"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }

        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.androidx.ui)
            implementation(libs.androidx.ui.tooling)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.foundation.layout.android)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].compilerOptions.options.freeCompilerArgs.add("-Xexport-kdoc")
    }

}

android {
    namespace = property("group").toString() + ".uikit"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

compose {
    resources {
        publicResClass = false
        generateResClass = always
    }
}