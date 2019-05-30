plugins {
    id("com.android.application")
    id("kotlin-android-extensions")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(BuildConstants.compileSdkVersion)
    defaultConfig {
        applicationId = BuildConstants.applicationId
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
    implementation(project(Dependencies.domain_objects))
    implementation(project(Dependencies.io))
    implementation(project(Dependencies.response_objects)) // TODO temporary. App will eventually get domain objects fully formed out of a facade

    implementation(Dependencies.kotlin_stdlib)

    implementation(Dependencies.appCompat)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.recyclerView)
    implementation(Dependencies.ktx)
    implementation(Dependencies.androidXLegacy)

    // RxJava
    implementation(Dependencies.rxJava)
    implementation(Dependencies.rxAndroid)
    implementation(Dependencies.rxKotlin)

    // Dependency injection
    implementation(Dependencies.dagger)
    kapt(Dependencies.dagger_apt)
    implementation(Dependencies.dagger_android)
//    implementation(Dependencies.dagger_android_support)
    kapt(Dependencies.dagger_android_apt)


    // Networking dependencies
    implementation(Dependencies.okhttp)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitRxAdapter)
    implementation(Dependencies.moshi)
    implementation(Dependencies.retrofitMoshiAdapter)
    kapt (Dependencies.moshiKapt)

    // Java Time backport
    implementation(Dependencies.threeTenAndroid)
    // Allows the time backport to work during testing
    testImplementation(Dependencies.threeTenJava)

    // Picasso
    implementation(Dependencies.picasso)

    // Test Dependencies
    testImplementation(Dependencies.robolectric)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.testRunner)
    androidTestImplementation(Dependencies.espressoCore)
}

repositories {
    mavenCentral()
}
