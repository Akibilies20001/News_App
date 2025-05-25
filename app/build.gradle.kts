plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id ("kotlin-parcelize")


}

android {
    namespace = "com.merajhossen20001.newsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.merajhossen20001.newsapp"
        minSdk = 27
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}



dependencies {


    // Splash API
    implementation ("androidx.core:core-splashscreen:1.0.1")
    implementation(libs.testng)

// Compose Navigation
    val nav_version = "2.8.9" // Latest stable as of Feb 2024
    implementation ("androidx.navigation:navigation-compose:$nav_version")

// Dagger Hilt
    val hilt_version = "2.50" // Latest stable version
    implementation ("com.google.dagger:hilt-android:2.56.2")
    kapt ("com.google.dagger:hilt-compiler:2.56.2")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

// Retrofit
    val retrofit_version = "2.11.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")

// Coil
    implementation ("io.coil-kt:coil-compose:2.7.0") // Latest stable version


// Datastore
    implementation("androidx.datastore:datastore-preferences:1.1.4")

// Compose Foundation
    implementation("androidx.compose.foundation:foundation:1.7.8")

// Accompanist (ensure it's compatible with Compose version)
    val accompanistVersion = "0.36.0" // Use Beta instead of Alpha for better stability
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")

// Paging 3
    val pagingVersion = "3.3.6"
    implementation("androidx.paging:paging-runtime:$pagingVersion")
    implementation("androidx.paging:paging-compose:$pagingVersion")

// Room Database
    val roomVersion = "2.7.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    implementation ("com.google.guava:guava:30.1-android")
    implementation ("androidx.concurrent:concurrent-futures:1.1.0")


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}


