plugins {
    id("com.android.library")
    id("kotlin-android-extensions")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(BuildConstants.compileSdkVersion)
    defaultConfig {
        minSdkVersion(BuildConstants.minSdkVersion)
        targetSdkVersion(BuildConstants.targetSdkVersion)
        versionCode = BuildConstants.versionCode
        versionName = BuildConstants.versionName
        testInstrumentationRunner = BuildConstants.testInstrumentationRunner
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = BuildConstants.minifyEnabled
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin
    api(Dependencies.kotlin_stdlib)

    // Android
    api(Dependencies.appCompat)

    // RxJava
    api(Dependencies.rxJava)
    api(Dependencies.rxAndroid)
    api(Dependencies.rxKotlin)
    api(Dependencies.rxRelay)

    // Arrow
    api(Dependencies.arrow_core)

    // Dagger
    api(Dependencies.dagger)
    kapt(Dependencies.dagger_apt)

    // Java Time backport
    api(Dependencies.threeTenAndroid)
    // Allows the time backport to work during testing
    testImplementation(Dependencies.threeTenJava)

    // Testing
    testImplementation(Dependencies.robolectric)
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockk)
    androidTestImplementation(Dependencies.testRunner)
    androidTestImplementation(Dependencies.espressoCore)
}
