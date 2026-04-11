package com.mustafakoceerr.dashnote.feature.auth.impl.login

import androidx.compose.runtime.Immutable

@Immutable
internal data class LoginUiState(
    val isLoading: Boolean = false,
    val isLoginSuccessful: Boolean = false,
    val errorMessage: String? = null
)
