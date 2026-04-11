package com.mustafakoceerr.dashnote.data.auth.domain

import com.mustafakoceerr.dashnote.data.auth.repository.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ObserveCurrentUserIdUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<String?> {
        return authRepository.currentUser.map { user ->
            user?.id
        }
    }
}