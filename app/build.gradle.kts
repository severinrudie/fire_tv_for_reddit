// TODO update to use Dependencies references
plugins {
    id("com.android.application")
    id("kotlin-android-extensions")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "seveida.firetvforreddit"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Dependencies.kotlin_version}")

    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // RxJava
    implementation("io.reactivex.rxjava2:rxjava:2.2.8")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxkotlin:2.3.0")

    // Networking dependencies
    implementation("com.squareup.okhttp3:okhttp:3.13.1")
    implementation("com.squareup.retrofit2:retrofit:2.5.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.2.0")
    implementation("com.squareup.moshi:moshi:1.8.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.5.0")
    kapt ("com.squareup.moshi:moshi-kotlin-codegen:1.6.0")

    // Java Time backport
    implementation("com.jakewharton.threetenabp:threetenabp:1.1.2")
    // Allows the time backport to work during testing
    testImplementation("org.threeten:threetenbp:1.3.8")

    // Picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    // Test Dependencies
    testImplementation("org.robolectric:robolectric:4.2")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1")
}

repositories {
    mavenCentral()
}
