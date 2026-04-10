plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.mustafakoceerr.dashnote.feature.auth.api"
    compileSdk { version = release(36) { minorApiLevel = 1 } }
    defaultConfig { minSdk = 29 }
}

dependencies {
    // core:navigation modülündeki Nav3 ve Serialization özelliklerine erişmek için
    api(project(":core:navigation"))
}