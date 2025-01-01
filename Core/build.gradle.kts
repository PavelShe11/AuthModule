plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.jreleaser)
}

group = "ru.shelldev.auth_module.core"
version = "1.0"

kotlin {
    jvmToolchain(11)
    androidTarget {
        publishLibraryVariants("release")
    }

    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "core"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }

        androidMain.dependencies {

        }

        jvmMain.dependencies {

        }

        iosMain.dependencies {

        }

    }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].compilerOptions.options.freeCompilerArgs.add("-Xexport-kdoc")
    }

}

android {
    namespace = "ru.shelldev.auth_module.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }
}

tasks.register("prepareJreleaserOutputDir") {
    doLast {
        val outputDir = layout.buildDirectory.file("jreleaser").get().asFile
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }
    }
}

tasks.named("jreleaserConfig").configure {
    dependsOn("prepareJreleaserOutputDir")
}

jreleaser {
    dryrun.set(true)
    project {
        name.set("AuthModule")
        version.set("1.0.0")
        description.set("OAuth 2.0 Module for secure authentication")
    }
    release {
        github {
            skipRelease.set(true)
            skipTag.set(true)
        }
    }
}
