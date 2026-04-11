package com.mustafakoceerr.dashnote.data.auth.domain

import com.mustafakoceerr.dashnote.core.common.Result
import com.mustafakoceerr.dashnote.data.auth.error.AuthError
import com.mustafakoceerr.dashnote.data.auth.repository.AuthRepository
import jakarta.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit, AuthError.SignOut> {
        return authRepository.signOut()
    }
}