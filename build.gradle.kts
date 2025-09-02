// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Consider if you still need these classpath dependencies
        // if you are defining plugins using the plugins {} block below
        // and referencing versions from your TOML file.
        // For modern Gradle, the plugins {} block is preferred.
        // classpath("com.android.tools.build:gradle:8.6.0") // Potentially redundant
        // classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.24") // Potentially redundant
        classpath("com.google.gms:google-services:4.4.3")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}


tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
    