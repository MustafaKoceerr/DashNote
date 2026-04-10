package com.mustafakoceerr.dashnote.data.auth.repository

interface AuthRepository {
    val currentUser: Flow<AuthUser?>
    suspend fun signInWithGoogle(idToken: String): Result<AuthUser, AuthError.SignIn>
    suspend fun signOut(): Result<Unit, AuthError.SignOut>
}