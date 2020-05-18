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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(Dependencies.common))
    implementation(project(Dependencies.domain_objects))
    implementation(project(Dependencies.response_objects))

    kapt(Dependencies.dagger_apt)

    // Allows the time backport to work during testing
    testImplementation(Dependencies.threeTenJava)

    implementation(Dependencies.okhttp)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitRxAdapter)
    implementation(Dependencies.moshi)
    implementation(Dependencies.retrofitMoshiAdapter)
    kapt (Dependencies.moshiKapt)

    implementation(Dependencies.jraw)

    // Testing
    testImplementation(Dependencies.robolectric)
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockk)
    androidTestImplementation(Dependencies.testRunner)
    androidTestImplementation(Dependencies.espressoCore)
}
