import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
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
            api(projects.common)
            api(projects.uikit)

            implementation(projects.featureAuth.ui)

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
            api(libs.koin.annotations)

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

compose.resources {
    publicResClass = false
    generateResClass = always
}

project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}