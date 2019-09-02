plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.wimank.craftmaster.tz"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    val kotlinVer = "1.3.50"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVer")
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")


    //RxJava
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.12")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")

    //Moxy
    val moxyVer = "1.7.0"
    implementation ("tech.schoolhelper:moxy-x-androidx:$moxyVer")
    implementation ("tech.schoolhelper:moxy-x-material:$moxyVer")
    kapt ("com.arello-mobile:moxy-compiler:1.5.5")

    //Room
    val roomVersion = "2.2.0-beta01"
    implementation ("androidx.room:room-runtime:$roomVersion")
    implementation ("androidx.room:room-rxjava2:$roomVersion")
    annotationProcessor ("androidx.room:room-compiler:$roomVersion")

    //Retrofit
    val retrofitVersion = "2.6.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("com.squareup.retrofit2:adapter-rxjava:$retrofitVersion")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.0.1")

    //Dagger 2
    val daggerVerdion = "2.22.1"
    api ("com.google.dagger:dagger-android:$daggerVerdion")
    api ("com.google.dagger:dagger-android-support:$daggerVerdion")
    kapt ("com.google.dagger:dagger-android-processor:$daggerVerdion")
}
