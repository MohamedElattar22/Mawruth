plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")

}

android {
    namespace = "com.graduation.mawruth"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.graduation.mawruth"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.makeramen:roundedimageview:2.3.0")
    //ssp & sdp
    implementation ("com.intuit.ssp:ssp-android:1.1.0")
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    //Coil
    implementation("io.coil-kt:coil:2.5.0")

    // viewModel and LiveData Dependencies
    val lifecycle_version = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    //Kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")


    //Circular Progress Indicator
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //Hilt DI
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    //shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    implementation ("com.github.bumptech.glide:glide:4.16.0")


    //ARCore
    implementation ("com.google.ar:core:1.40.0")
    //SceneForm
    // Provides ArFragment, and other UX resources.
    implementation("com.google.ar.sceneform.ux:sceneform-ux:1.17.1")
    implementation("com.google.ar.sceneform:assets:1.17.1")
    implementation("com.google.ar.sceneform:animation:1.17.1")
    // Alternatively, use ArSceneView without the UX dependency.
    implementation("com.google.ar.sceneform:core:1.17.1")

    //circle indicator
    implementation("me.relex:circleindicator:2.1.6")

    val lottieVersion = "6.3.0"
    implementation("com.airbnb.android:lottie:$lottieVersion")

}