plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("com.google.dagger.hilt.android")
    id ("com.google.devtools.ksp")
}

android {
    namespace = "com.emapps.whattheforecast"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.emapps.whattheforecast"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation (libs.androidx.fragment.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.hilt.android) //Hilt
    ksp (libs.hilt.android.compiler) //Hilt
    implementation (libs.converter.moshi) //Moshi
    implementation (libs.retrofit) //Retrofit
    implementation (libs.logging.interceptor) //Retrofit
    implementation (libs.shimmer) //Shimmer
    implementation (libs.glide) //Glide
    annotationProcessor (libs.compiler) //Glide
}