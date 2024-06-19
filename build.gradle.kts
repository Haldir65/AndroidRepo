// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        // In Android, all plugins are found in the google() and mavenCentral() repositories.
        // However, your build might need third-party plugins that are resolved using the gradlePluginPortal() service.
        google()
        mavenCentral()
        gradlePluginPortal()
        // Therefore, experiment with the gradlePluginPortal() entry
        // by putting it last in the repository block in your settings.gradle file.
    }
}

plugins {
    // Existing plugins
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
}


