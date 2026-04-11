package com.mustafakoceerr.dashnote.data.auth.model

/**
 * Uygulamanın her yerinde (SSOT) kullanılacak olan saf Kullanıcı modeli.
 * FirebaseUser sınıfını doğrudan UI'a sızdırmamak için bu wrapper'ı kullanıyoruz.
 */
data class AuthUser(
    val id: String,
    val email: String?,
    val displayName: String?,
    val profilePictureUrl: String?
)