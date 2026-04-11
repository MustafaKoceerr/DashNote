package com.mustafakoceerr.dashnote.feature.auth.impl.domain


import com.mustafakoceerr.dashnote.core.common.Result
import com.mustafakoceerr.dashnote.data.auth.error.AuthError
import com.mustafakoceerr.dashnote.data.auth.model.AuthUser
import com.mustafakoceerr.dashnote.data.auth.repository.AuthRepository
import javax.inject.Inject

internal class SignInWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): Result<AuthUser, AuthError.SignIn> {
        // İleride buraya business logic (iş kuralları) eklenebilir.
        return authRepository.signInWithGoogle(idToken)
    }
}