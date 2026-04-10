package com.mustafakoceerr.dashnote.data.auth.model

/**
 * Uygulama genelinde kullanılacak saf Kullanıcı nesnesi.
 * Firebase veya herhangi bir kütüphane bağımlılığı içermez.
 */
data class AuthUser(
    val id: String,
    val email: String?,
    val displayName: String?,
    val profilePictureUrl: String?
)