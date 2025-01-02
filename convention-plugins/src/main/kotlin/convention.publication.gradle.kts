import com.vanniktech.maven.publish.SonatypeHost

plugins {
    `maven-publish`
    signing
    id("com.vanniktech.maven.publish")
}

publishing.publications
    .withType<MavenPublication>()
    .configureEach {
        pom {
            description = "Механизм аутентификации и авторизации"
            url = "https://github.com/PavelShe11/AuthModule"

            licenses {
                license {
                    name = "Apache 2.0"
                    url = "https://opensource.org/license/apache-2-0"
                }
            }

            developers {
                developer {
                    id = "PavelShe11"
                    name = "Pavel Sheludyakov"
                    email = "technicianpavelshell@gmail.com"
                }
            }

            scm {
                url = "https://github.com/PavelShe11/AuthModule"
                connection = "scm:git:git://github.com/PavelShe11/AuthModule.git"
                developerConnection = "scm:git:ssh://git@github.com/PavelShe11/AuthModule.git"
            }
        }
    }

publishing {
    repositories {
        mavenLocal()

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/PavelShe11/AuthModule")

            credentials {
                username = getVarFromPropOrEnv("GITHUB_ACTOR")
                password = getVarFromPropOrEnv("GITHUB_TOKEN")
            }
        }
    }
}

signing {
    if (getVarFromPropOrEnv("signing.keyId") != null) {
        sign(publishing.publications)
    }
}

val signingTasks = tasks.withType<Sign>()
tasks.withType<AbstractPublishToMaven>().configureEach {
    mustRunAfter(signingTasks)
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = false)
    signAllPublications()
}

fun getVarFromPropOrEnv(name: String): String? {
    return properties[name]?.toString() ?: System.getenv(name)
}