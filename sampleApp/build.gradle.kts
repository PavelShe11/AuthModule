
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.serialization)
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

            export(libs.decompose)

            export(libs.essenty.lifecycle)
            export(libs.essenty.stateKeeper)

            export(libs.koin.core)
        }
    }

    sourceSets {
        commonMain.dependencies {
            //Project dependencies
            implementation(project(":oauth_2_0:impl"))
            implementation(project(":oauth_2_0:ktor"))

            implementation(project(":sampleApp:common"))
            implementation(project(":sampleApp:uikit"))
            implementation(project(":sampleApp:umbrella"))

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
            api(libs.koin.core)
            implementation(project.dependencies.platform(libs.koin.annotations.bom))
            implementation(libs.koin.annotations)

            //mvikotlin
            implementation(libs.mvikotlin)
            implementation(libs.mvikotlin.main)
            implementation(libs.mvikotlin.logging)
            implementation(libs.mvikotlin.extensions.coroutines)

            //Navigation (decompose)
            api(libs.decompose)
            implementation(libs.decompose.compose)

            //essenty
            implementation(libs.essenty.lifecycle.coroutines)
            api(libs.essenty.lifecycle)
            api(libs.essenty.stateKeeper)
        }

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
    }

    sourceSets.named("commonMain").configure {
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)

    add("kspAndroid", libs.koin.ksp.compiler)

    add("kspIosX64", libs.koin.ksp.compiler)
    add("kspIosArm64", libs.koin.ksp.compiler)
    add("kspIosSimulatorArm64", libs.koin.ksp.compiler)
}

project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

android {
    namespace = moduleGroup
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = property("group").toString() + ".sample"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

compose {
    resources {
        publicResClass = false
        generateResClass = always
    }
}

project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}