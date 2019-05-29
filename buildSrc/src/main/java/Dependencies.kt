object Dependencies {
    const val kotlin_version = "1.3.21"
    const val gradle_plugin = "com.android.tools.build:gradle:3.4.0"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"

    // Language
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"

    // Android
    const val appCompat = "androidx.appcompat:appcompat:1.0.2"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.0.0"
    const val ktx = "androidx.core:core-ktx:1.0.2"
    const val androidXLegacy = "androidx.legacy:legacy-support-v4:1.0.0"

    // RxJava
    const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.8"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.3.0"

    // Networking dependencies
    const val okhttp = "com.squareup.okhttp3:okhttp:3.13.1"
    const val retrofit = "com.squareup.retrofit2:retrofit:2.5.0"
    const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:2.2.0"
    const val moshi = "com.squareup.moshi:moshi:1.8.0"
    const val retrofitMoshiAdapter = "com.squareup.retrofit2:converter-moshi:2.5.0"
    const val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:1.6.0"

    // Java Time backport
    const val threeTenAndroid = "com.jakewharton.threetenabp:threetenabp:1.1.2"
    // Allows the time backport to work during testing
    const val threeTenJava = "org.threeten:threetenbp:1.3.8"

    // Picasso
    const val picasso = "com.squareup.picasso:picasso:2.71828"

    // Test Dependencies
    const val robolectric = "org.robolectric:robolectric:4.2"
    const val junit = "junit:junit:4.12"
    const val testRunner = "androidx.test:runner:1.1.1"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.1.1"
}