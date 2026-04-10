plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.mustafakoceerr.dashnote.core.navigation"
    compileSdk { version = release(36) { minorApiLevel = 1 } }
    defaultConfig { minSdk = 29 }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    // Senin eklediğin Nav3 kütüphanelerini doğrudan "api" olarak dışarı açıyoruz
    api(libs.androidx.navigation3.runtime)
    api(libs.androidx.navigation3.ui)

    // Type-Safe Route objeleri (@Serializable) için
    api(libs.kotlinx.serialization.json)
}