package com.mustafakoceerr.dashnote.core.designsystem.util

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf

/**
 * Global SnackbarHostState'i Compose ağacı boyunca taşımak için kullanılan anahtar.
 */
val LocalSnackbarHostState = compositionLocalOf<SnackbarHostState> {
    error("SnackbarHostState henüz başlatılmadı! (App seviyesinde provide edilmeli)")
}