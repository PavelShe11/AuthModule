import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.compose.multiplatform)
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
            implementation(projects.uikit)

            //UI (compose)
            implementation(compose.ui)
            implementation(compose.material3)

            //mvikotlin
            implementation(libs.mvikotlin)

            //Navigation (decompose)
            implementation(libs.decompose)

            //DI (koin)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(project.dependencies.platform(libs.koin.annotations.bom))
            api(libs.koin.annotations)

            //Navigation (decompose)
            implementation(libs.decompose)
            implementation(libs.decompose.compose)
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

project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
