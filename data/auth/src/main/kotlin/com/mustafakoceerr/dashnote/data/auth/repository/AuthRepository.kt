package com.mustafakoceerr.dashnote.data.auth.repository

import com.mustafakoceerr.dashnote.data.auth.error.AuthError
import com.mustafakoceerr.dashnote.data.auth.model.AuthUser
import kotlinx.coroutines.flow.Flow
import com.mustafakoceerr.dashnote.core.common.Result

interface AuthRepository {
    val currentUser: Flow<AuthUser?>
    suspend fun signInWithGoogle(idToken: String): Result<AuthUser, AuthError.SignIn>
    suspend fun signOut(): Result<Unit, AuthError.SignOut>
}