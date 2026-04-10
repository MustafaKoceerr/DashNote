package com.mustafakoceerr.dashnote.data.auth.source

import com.mustafakoceerr.dashnote.data.auth.error.AuthError
import com.mustafakoceerr.dashnote.data.auth.model.AuthUser
import kotlinx.coroutines.flow.Flow
import com.mustafakoceerr.dashnote.core.common.Result
/**
 * Uzak sunucu (Firebase) ile konuşacak sözleşmemiz.
 */
interface AuthDataSource {
    /** Anlık giriş yapmış kullanıcıyı dinleyen Flow */
    val currentUser: Flow<AuthUser?>

    /** Google Credential Manager'dan alınan Token ile Firebase'e giriş yapar */
    suspend fun signInWithGoogle(idToken: String): Result<AuthUser, AuthError.SignIn>

    suspend fun signOut(): Result<Unit, AuthError.SignOut>
}