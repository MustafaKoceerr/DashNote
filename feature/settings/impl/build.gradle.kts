plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.mustafakoceerr.dashnote.feature.settings.impl"
    compileSdk { version = release(36) { minorApiLevel = 1 } }
    defaultConfig { minSdk = 29 }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    debugImplementation(libs.androidx.compose.ui.tooling)

    // Navigasyon Altyapısı ve Kendi Sözleşmesi (API'si)
    implementation(project(":core:navigation"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:settings:api"))

    // Geri dönmek için Navigator'ın goBack() fonksiyonunu kullanacağız,
    // bu yüzden başka hiçbir modülün API'sine ihtiyacımız YOK!
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}