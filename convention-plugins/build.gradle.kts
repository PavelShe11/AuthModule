plugins {
    `kotlin-dsl` // Is needed to turn our build logic written in Kotlin into Gralde Plugin
}

repositories {
    gradlePluginPortal() // To use 'maven-publish' and 'signing' plugins in our own plugin
}

dependencies {
    //noinspection UseTomlInstead
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.31.0")
}
