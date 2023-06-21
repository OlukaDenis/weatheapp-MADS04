object Dependencies {

    object Test {
        const val CORE = "androidx.test:core:${Versions.Test.CORE}"
        const val RULES = "androidx.test:rules:${Versions.Test.CORE}"
        const val JUNIT = "junit:junit:${Versions.Test.JUNIT}"
        const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.Test.JUNIT_EXT}"
        const val JACOCO = "org.jacoco:org.jacoco.core:${Versions.Test.JACOCO}"
        const val TRUTHY = "com.google.truth:truth:${Versions.Test.TRUTHY}"
        const val MOCKK = "io.mockk:mockk:${Versions.Test.MOCKK}"
        const val JACOCO_ANDROID = "com.dicedmelon.gradle:jacoco-android:${Versions.Test.JACOCO_ANDROID}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO}"
        const val CORE_TESTING = "androidx.arch.core:core-testing:${Versions.Test.CORE_TESTING}"
        const val ROBOELECTRIC = "org.robolectric:robolectric:${Versions.Test.ROBOELECTRIC}"
    }
}