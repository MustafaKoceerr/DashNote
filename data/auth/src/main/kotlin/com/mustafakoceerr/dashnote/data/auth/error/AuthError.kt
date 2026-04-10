package com.mustafakoceerr.dashnote.data.auth.error

import com.mustafakoceerr.dashnote.core.common.error.RootError

sealed interface AuthError : RootError {
    enum class SignIn : AuthError {
        INVALID_CREDENTIALS,
        USER_CANCELED,
        NETWORK_ISSUE,
        TOO_MANY_REQUESTS,
        UNKNOWN
    }

    enum class SignOut : AuthError {
        UNKNOWN
    }
}