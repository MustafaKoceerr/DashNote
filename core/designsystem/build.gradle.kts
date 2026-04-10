plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.compose) // Sadece Compose compiler plugin'i kalıyor
}

android {
    namespace = "com.mustafakoceerr.dashnote.core.designsystem"

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

    // Compose BoM ve Temel UI Kütüphaneleri
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Tüm ikonları bütün projeye tek bir yerden dağıtıyoruz
    api(libs.androidx.compose.material.icons.extended)
}