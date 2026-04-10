plugins {
    id("com.android.library")
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.mustafakoceerr.dashnote.core.common"

    // AGP 9.1 modern compileSdk tanımlaması
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 29
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    // Hilt (İleride Dispatcher'ları veya temel sınıfları sağlamak için)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Coroutines (Result wrapper içindeki flow/suspend yapıları veya Dispatcher'lar için)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
}