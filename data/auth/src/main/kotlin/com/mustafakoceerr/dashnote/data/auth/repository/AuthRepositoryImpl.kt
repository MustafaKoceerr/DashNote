package com.mustafakoceerr.dashnote.data.auth.repository

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.mustafakoceerr.dashnote.core.common.Result
import com.mustafakoceerr.dashnote.core.common.dispatcher.DashNoteDispatchers
import com.mustafakoceerr.dashnote.core.common.dispatcher.Dispatcher
import com.mustafakoceerr.dashnote.data.auth.error.AuthError
import com.mustafakoceerr.dashnote.data.auth.model.AuthUser
import com.mustafakoceerr.dashnote.data.auth.source.remote.FirebaseAuthDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: FirebaseAuthDataSource,
    @Dispatcher(DashNoteDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {

    override val currentUser: Flow<AuthUser?> = remoteDataSource.currentUser

    override suspend fun signInWithGoogle(idToken: String): Result<AuthUser, AuthError.SignIn> {
        return withContext(ioDispatcher) {
            try {
                val user = remoteDataSource.signInWithGoogle(idToken)
                Result.Success(user)
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                Result.Error(AuthError.SignIn.INVALID_CREDENTIALS)
            } catch (e: FirebaseNetworkException) {
                Result.Error(AuthError.SignIn.NETWORK_ISSUE)
            } catch (e: Exception) {
                Result.Error(AuthError.SignIn.UNKNOWN)
            }
        }
    }

    override suspend fun signOut(): Result<Unit, AuthError.SignOut> {
        return withContext(ioDispatcher) {
            try {
                remoteDataSource.signOut()
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(AuthError.SignOut.UNKNOWN)
            }
        }
    }
}