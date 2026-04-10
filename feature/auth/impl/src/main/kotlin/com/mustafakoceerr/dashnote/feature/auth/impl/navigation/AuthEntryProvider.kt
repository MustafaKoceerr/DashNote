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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.mustafakoceerr.dashnote.core.navigation.Navigator
import com.mustafakoceerr.dashnote.feature.auth.api.navigation.AuthRoute
import com.mustafakoceerr.dashnote.feature.notes.api.navigation.NotesRoute

fun EntryProviderScope<NavKey>.authEntry(navigator: Navigator) {
    entry<AuthRoute> {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(80.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(16.dp))
            Text("DashNote'a Hoş Geldiniz", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("Lütfen devam etmek için giriş yapın.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navigator.navigate(NotesRoute) },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Google ile Giriş Yap")
            }
        }
    }
}