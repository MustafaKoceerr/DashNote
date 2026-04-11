package com.mustafakoceerr.dashnote.data.auth.error

import com.mustafakoceerr.dashnote.core.common.error.RootError

sealed interface AuthError : RootError {
    enum class SignIn : AuthError {
        INVALID_CREDENTIALS,
        NETWORK_ISSUE,
        CANCELED,
        UNKNOWN
    }

    enum class SignOut : AuthError {
        UNKNOWN
    }
}