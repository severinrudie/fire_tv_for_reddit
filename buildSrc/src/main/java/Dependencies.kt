object Dependencies {
    val kotlin_version = "1.3.21"
    val gradle_plugin = "com.android.tools.build:gradle:3.4.0"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"

    // Language
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"

    // Android
    val appCompat = "androidx.appcompat:appcompat:1.0.2"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    val recyclerView = "androidx.recyclerview:recyclerview:1.0.0"
    val ktx = "androidx.core:core-ktx:1.0.2"
    val androidXLegacy = "androidx.legacy:legacy-support-v4:1.0.0"

    // RxJava
    val rxJava = "io.reactivex.rxjava2:rxjava:2.2.8"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.3.0"

    // Networking dependencies
    val okhttp = "com.squareup.okhttp3:okhttp:3.13.1"
    val retrofit = "com.squareup.retrofit2:retrofit:2.5.0"
    val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:2.2.0"
    val moshi = "com.squareup.moshi:moshi:1.8.0"
    val retrofitMoshiAdapter = "com.squareup.retrofit2:converter-moshi:2.5.0"
    val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:1.6.0"

    // Java Time backport
    val threeTenAndroid = "com.jakewharton.threetenabp:threetenabp:1.1.2"
    // Allows the time backport to work during testing
    val threeTenJava = "org.threeten:threetenbp:1.3.8"

    // Picasso
    val picasso = "com.squareup.picasso:picasso:2.71828"

    // Test Dependencies
    val robolectric = "org.robolectric:robolectric:4.2"
    val junit = "junit:junit:4.12"
    val testRunner = "androidx.test:runner:1.1.1"
    val espressoCore = "androidx.test.espresso:espresso-core:3.1.1"
}