package com.mustafakoceerr.dashnote.feature.auth.impl.navigation

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.mustafakoceerr.dashnote.core.designsystem.util.LocalSnackbarHostState
import com.mustafakoceerr.dashnote.core.navigation.Navigator
import com.mustafakoceerr.dashnote.feature.auth.api.navigation.AuthRoute
import com.mustafakoceerr.dashnote.feature.auth.impl.login.LoginRoute
import com.mustafakoceerr.dashnote.feature.notes.api.navigation.NotesRoute
import kotlinx.coroutines.launch

fun EntryProviderScope<NavKey>.authEntry(navigator: Navigator) {
    entry<AuthRoute> {
        // Ağacın tepesinden (DashNoteApp) gelen global Snackbar'ı yakala
        val snackbarHostState = LocalSnackbarHostState.current
        val scope = rememberCoroutineScope()

        LoginRoute(
            onNavigateToNotes = { navigator.navigate(NotesRoute) },
            onShowSnackbar = { message ->
                // Snackbar gösterme işlemi suspend olduğu için coroutine içinde çağırıyoruz
                scope.launch {
                    snackbarHostState.showSnackbar(message)
                }
            }
        )
    }
}