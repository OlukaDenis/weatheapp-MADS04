plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.mcdenny.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"${Versions.BASE_URL}\"")
        }

        release {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"${Versions.BASE_URL}\"")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO}")

    testImplementation(Dependencies.Test.TRUTHY)
    testImplementation(Dependencies.Test.JUNIT)
    testImplementation(Dependencies.Test.MOCKK)
    testImplementation(Dependencies.Test.JUNIT_EXT)

    implementation("com.squareup.retrofit2:retrofit:${Versions.Network.RETROFIT}")
    implementation("com.squareup.okhttp3:okhttp:${Versions.Network.OKHTTP}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.Network.OKHTTP}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.Network.RETROFIT}")
    testImplementation("com.squareup.okhttp3:mockwebserver:${Versions.Network.OKHTTP}")

    implementation("com.google.dagger:hilt-android:${Versions.Hilt.HILT_CORE}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.Hilt.HILT_CORE}")

    implementation("androidx.room:room-runtime:${Versions.Room.CORE_KTX}")
    kapt("androidx.room:room-compiler:${Versions.Room.CORE_KTX}")

    implementation("com.jakewharton.timber:timber:${Versions.Util.TIMBER}")

    implementation(project(":domain"))
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}