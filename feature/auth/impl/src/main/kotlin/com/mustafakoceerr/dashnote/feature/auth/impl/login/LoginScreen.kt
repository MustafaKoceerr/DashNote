package com.mustafakoceerr.dashnote.feature.auth.impl.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafakoceerr.dashnote.feature.auth.impl.login.util.GoogleAuthClient
import kotlinx.coroutines.launch

/**
 * Navigasyon (EntryProvider) tarafından çağrılan State Stateful kapsayıcı (Wrapper).
 */
@Composable
internal fun LoginRoute(
    onNavigateToNotes: () -> Unit,
    onShowSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    // UI Katmanı Yardımcı Sınıfları
    val context = LocalContext.current
    val googleAuthClient = remember { GoogleAuthClient(context) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(uiState.isLoginSuccessful) {
        if (uiState.isLoginSuccessful) {
            onShowSnackbar("Giriş Başarılı! Yönlendiriliyorsunuz...")
            onNavigateToNotes()
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { errorMsg ->
            onShowSnackbar(errorMsg)
            viewModel.onErrorConsumed()
        }
    }

    LoginScreen(
        uiState = uiState,
        onGoogleSignInClick = {
            // Askıya alınabilir (suspend) işlemi coroutine içinde başlatıyoruz
            scope.launch {
                try {
                    val idToken = googleAuthClient.signIn()
                    if (idToken != null) {
                        // Kullanıcı başarıyla token aldı, şimdi bunu Business Logic'e (ViewModel'a) verelim!
                        viewModel.onGoogleSignIn(idToken)
                    } else {
                        // Kullanıcı iptal etti, YAGNI gereği sessizce es geçiyoruz.
                    }
                } catch (e: Exception) {
                    onShowSnackbar("Google hizmetine bağlanılamadı: ${e.localizedMessage}")
                }
            }
        },
        modifier = modifier
    )
}

/**
 * Saf (Stateless) UI bileşeni. İçinde ViewModel veya CoroutineScope barındırmaz.
 */
@Composable
internal fun LoginScreen(
    uiState: LoginUiState,
    onGoogleSignInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(80.dp), tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))
        Text("DashNote'a Hoş Geldiniz", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(32.dp))

        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(onClick = onGoogleSignInClick, modifier = Modifier.fillMaxWidth().height(50.dp)) {
                Text("Google ile Giriş Yap")
            }
        }
    }
}