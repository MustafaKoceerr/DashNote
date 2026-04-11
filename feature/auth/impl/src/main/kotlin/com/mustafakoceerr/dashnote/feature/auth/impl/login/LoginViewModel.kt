package com.mustafakoceerr.dashnote.feature.auth.impl.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafakoceerr.dashnote.core.common.Result
import com.mustafakoceerr.dashnote.feature.auth.impl.domain.SignInWithGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onGoogleSignIn(idToken: String) {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            when (val result = signInWithGoogleUseCase(idToken)) {
                is Result.Success -> {
                    _uiState.update { it.copy(isLoading = false, isLoginSuccessful = true) }
                }

                is Result.Error -> {
                    // TODO: İleride bu error enum'unu string resource'lara çeviren bir mapper yazacağız.
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Giriş başarısız oldu. Hata kodu: ${result.error.name}"
                        )
                    }
                }
            }
        }
    }

    // UI hata mesajını gösterdikten (Snackbar) sonra state'i temizler.
    fun onErrorConsumed() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}