plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.mcdenny.nssfweather"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mcdenny.nssfweather"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation("androidx.core:core-ktx:1.8.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.fragment:fragment-ktx:${Versions.AndroidX.FRAGMENT_KTX}")
    implementation("androidx.activity:activity-ktx:${Versions.AndroidX.ACTIVITY_KTX}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.LIFECYCLE_KTX}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.LIFECYCLE_KTX}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.LIFECYCLE_KTX}")

//    testImplementation("junit:junit:${Versions.Test.JUNIT}")
//    androidTestImplementation("androidx.test.ext:junit:${Versions.Test.EXT_JUNIT}")
//    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO}")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.COROUTINES}")

    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation("com.google.dagger:hilt-android:${Versions.Hilt.HILT_CORE}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.Hilt.HILT_CORE}")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}