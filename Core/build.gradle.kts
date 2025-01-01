
import org.jreleaser.model.Active

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.jreleaser)
}

group = "ru.pavelshe11.auth_module"
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
        createDirectory("jreleaser")
        createDirectory("staging-deploy")
    }
}

fun createDirectory(name: String) {
    val outputDir = layout.buildDirectory.file(name).get().asFile
    if (!outputDir.exists()) {
        outputDir.mkdirs()
    }
}

tasks.named("jreleaserConfig") {
    dependsOn("prepareJreleaserOutputDir")
}
tasks.named("jreleaserFullRelease") {
    dependsOn("prepareJreleaserOutputDir")
}

jreleaser {
    gitRootSearch.set(true)
    project {
        inceptionYear = "2025"
        author("@PavelShe11")
        license = "http://www.apache.org/licenses/LICENSE-2.0.txt"
        version = "1.0.0"
        description = "api через которое можно получить доступ к механизму аутентификации и авторизации"
    }

    signing {
        active = Active.ALWAYS
        armored = true
        verify = true
    }

    release {
        github {
            skipRelease = true
            skipTag = true
            sign = true
            branch = "main"
            branchPush = "main"
            overwrite = true
        }
    }

    deploy {
        maven {
            mavenCentral.create("sonatype") {
                active = Active.ALWAYS
                url = "https://central.sonatype.com/api/v1/publisher"
                stagingRepository(layout.buildDirectory.dir("staging-deploy").get().toString())
                setAuthorization("Basic")

                applyMavenCentralRules = false
                sign = true
                checksums = true
                sourceJar = true
                javadocJar = true

                retryDelay = 60
            }

        }
    }
}
