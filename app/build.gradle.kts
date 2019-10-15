import de.mannodermaus.gradle.plugins.junit5.junitPlatform

plugins {
    id("com.android.application")
    id("de.mannodermaus.android-junit5")
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
        testInstrumentationRunnerArgument(
            "runnerBuilder",
            "de.mannodermaus.junit5.AndroidJUnit5Builder"
        )
    }

    sourceSets {
        this.forEach {
            it.java.srcDir("src/$it.name/kotlin")
        }
    }

    testOptions {
        junitPlatform {
            filters {
                includeEngines("spek2")
            }
        }

        unitTests.all(KotlinClosure1<Any, Test>({
            (this as Test).also { testTask ->
                testTask.testLogging {
                    events("passed", "skipped", "failed")
                }
            }
        }, this))
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("META-INF/LICENSE*")
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
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVer")

    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("com.google.android.material:material:1.0.0")

    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    //RxJava
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.12")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.6.1")

    //Moxy
    val moxyVer = "1.7.0"
    implementation("tech.schoolhelper:moxy-x-androidx:$moxyVer")
    implementation("tech.schoolhelper:moxy-x:$moxyVer")
    kapt("tech.schoolhelper:moxy-x-compiler:$moxyVer")

    //Room
    val roomVersion = "2.2.0-beta01"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-rxjava2:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    //Retrofit
    val retrofitVersion = "2.6.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:4.0.1")

    //Dagger 2
    val daggerVersion = "2.24"
    implementation("com.google.dagger:dagger:$daggerVersion")
    implementation("com.google.dagger:dagger-android:$daggerVersion")
    implementation("com.google.dagger:dagger-android-support:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")
    kapt("com.google.dagger:dagger-android-processor:$daggerVersion")

    //Glide
    val glideVer = "4.9.0"
    implementation("com.github.bumptech.glide:glide:$glideVer")
    implementation("com.github.bumptech.glide:okhttp3-integration:$glideVer")
    kapt("com.github.bumptech.glide:compiler:$glideVer")

    //Stetho
    val stethoVer = "1.5.1"
    implementation("com.facebook.stetho:stetho:$stethoVer")
    implementation("com.facebook.stetho:stetho-okhttp3:$stethoVer")

    //Apache Collections
    implementation("org.apache.commons:commons-collections4:4.1")

    //JUnit5
    val junitVersion = "5.5.2"
    androidTestImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    androidTestImplementation("de.mannodermaus.junit5:android-test-core:1.1.0")
    androidTestRuntimeOnly("de.mannodermaus.junit5:android-test-runner:1.1.0")

    //Spek
    val spekVersion = "2.0.8"
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testImplementation("org.spekframework.spek2:spek-runner-junit5:$spekVersion")

    //Assertion
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVer")

    //Mockk
    val mockkVersion = "1.9.3.kotlin12"
    testImplementation("io.mockk:mockk:$mockkVersion")
    androidTestImplementation("io.mockk:mockk-android:$mockkVersion")

}
