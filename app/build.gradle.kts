plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
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
        debug {
            buildConfigField("String", "PLACES_KEY", "\"${Versions.MAPS_KEY}\"")
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", "PLACES_KEY", "\"${Versions.MAPS_KEY}\"")
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

    implementation("androidx.core:core-ktx:${Versions.AndroidX.CORE_KTX}")
    implementation ("androidx.appcompat:appcompat:${Versions.AndroidX.APP_COMPAT}")
    implementation ("com.google.android.material:material:${Versions.AndroidX.MATERIAL}")
    implementation ("androidx.constraintlayout:constraintlayout:${Versions.AndroidX.CONSTRAINT_LAYOUT}")

    implementation("androidx.fragment:fragment-ktx:${Versions.AndroidX.FRAGMENT_KTX}")
    implementation("androidx.activity:activity-ktx:${Versions.AndroidX.ACTIVITY_KTX}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.LIFECYCLE_KTX}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.LIFECYCLE_KTX}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.LIFECYCLE_KTX}")

    implementation("androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.NAVIGATION}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.NAVIGATION}")


//    testImplementation("junit:junit:${Versions.Test.JUNIT}")
//    androidTestImplementation("androidx.test.ext:junit:${Versions.Test.EXT_JUNIT}")
//    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO}")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.COROUTINES}")

    implementation("com.jakewharton.timber:timber:${Versions.Util.TIMBER}")
    implementation("io.coil-kt:coil:2.4.0")

    implementation("com.google.dagger:hilt-android:${Versions.Hilt.HILT_CORE}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.Hilt.HILT_CORE}")

    implementation("com.google.android.libraries.places:places:3.1.0")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}