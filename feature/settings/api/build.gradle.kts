plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.mustafakoceerr.dashnote.feature.settings.api"
    compileSdk { version = release(36) { minorApiLevel = 1 } }
    defaultConfig { minSdk = 29 }
}

dependencies {
    api(project(":core:navigation"))
}