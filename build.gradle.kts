// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath( "com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt.HILT_CORE}")
        classpath("androidx.navigation.safeargs:androidx.navigation.safeargs.gradle.plugin:${Versions.AndroidX.NAVIGATION}")
//        classpath(Dependencies.Hilt.CLASSPATH)
    }
}

plugins {
    id("com.android.application") version "7.3.0" apply false
    id("com.android.library") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
//    id("org.jetbrains.kotlin.jvm") version "1.7.10" apply false
}

