package com.mustafakoceerr.dashnote.feature.auth.impl.login.util

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.mustafakoceerr.dashnote.feature.auth.impl.R
import java.security.MessageDigest
import java.util.UUID

/**
 * UI katmanında yaşar ve Credential Manager'ı yönetir.
 * Context barındırdığı için ASLA ViewModel'a enjekte edilmez!
 */
internal class GoogleAuthClient(
    private val context: Context
) {

    companion object {
        private const val TAG = "GoogleAuthClient"
    }

    suspend fun signIn(): String? {
        val credentialManager = CredentialManager.create(context)

        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hashedNonce = digest.fold("") { str, byte -> str + "%02x".format(byte) }

        val serverClientId = context.getString(R.string.default_web_client_id)

        Log.d(TAG, "signIn started")
        Log.d(TAG, "serverClientId empty=${serverClientId.isBlank()}")
        Log.d(TAG, "nonceLength=${hashedNonce.length}")

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(serverClientId)
            .setNonce(hashedNonce)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        return try {
            Log.d(TAG, "Calling CredentialManager.getCredential()")

            val result = credentialManager.getCredential(
                request = request,
                context = context
            )

            val credential = result.credential
            Log.d(TAG, "Credential received. class=${credential::class.java.simpleName}, type=${credential.type}")

            if (
                credential is CustomCredential &&
                credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {
                try {
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    Log.d(TAG, "Google ID token parsed successfully")
                    googleIdTokenCredential.idToken
                } catch (e: GoogleIdTokenParsingException) {
                    Log.e(TAG, "Failed to parse Google ID token", e)
                    null
                }
            } else {
                Log.e(TAG, "Unexpected credential type. type=${credential.type}")
                null
            }
        } catch (e: NoCredentialException) {
            Log.e(TAG, "No credentials available", e)
            null
        } catch (e: GetCredentialCancellationException) {
            Log.d(TAG, "User cancelled credential flow", e)
            null
        } catch (e: GetCredentialException) {
            Log.e(TAG, "CredentialManager error: ${e.type}", e)
            null
        } catch (e: Exception) {
            Log.e(TAG, "Unknown sign-in error", e)
            throw e
        }
    }
}