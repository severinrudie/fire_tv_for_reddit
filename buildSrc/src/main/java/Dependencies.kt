object Dependencies {
    // Modules
    const val app = ":app"
    const val domain_objects = ":domain_objects"
    const val response_objects = ":response_objects"
    const val io = ":io"
    const val business_logic = ":business_logic"

    // Language
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${BuildConstants.kotlin_version}"

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

    // Dependency injection
    private const val dagger_version = "2.23"
    const val dagger = "com.google.dagger:dagger:$dagger_version"
    const val dagger_apt = "com.google.dagger:dagger-compiler:$dagger_version"
    const val dagger_android = "com.google.dagger:dagger-android:$dagger_version"
//    const val dagger_android_support = "com.google.dagger:dagger-android-support:$dagger_version"
    const val dagger_android_apt = "com.google.dagger:dagger-android-processor:$dagger_version"

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