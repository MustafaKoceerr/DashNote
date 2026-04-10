package com.mustafakoceerr.dashnote.feature.settings.impl.navigation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.mustafakoceerr.dashnote.core.navigation.Navigator
import com.mustafakoceerr.dashnote.feature.settings.api.navigation.SettingsRoute

fun EntryProviderScope<NavKey>.settingsEntry(navigator: Navigator) {
    entry<SettingsRoute> {
        SettingsScreen()
    }
}

/**
 * Material 3 standartlarında modern bir ayarlar ekranı.
 * İleride buradaki state'ler ViewModel ve DataStore (Offline-First) ile yönetilecek.
 */
@Composable
private fun SettingsScreen() {
    // Şimdilik UI'ın tepki vermesi için lokal state'ler (Fake State)
    var darkThemeEnabled by remember { mutableStateOf(true) }
    var dynamicColorEnabled by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("Görünüm", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))

        // 1. Karanlık Tema Seçeneği
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.DarkMode, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.width(16.dp))
                Text("Karanlık Tema", style = MaterialTheme.typography.bodyLarge)
            }
            Switch(
                checked = darkThemeEnabled,
                onCheckedChange = { darkThemeEnabled = it }
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        // 2. Dinamik Renk Seçeneği (Android 12+)
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Icon(Icons.Default.Palette, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("Dinamik Renkler", style = MaterialTheme.typography.bodyLarge)
                    Text(
                        "Duvar kağıdına uyumlu sistem renkleri",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Switch(
                checked = dynamicColorEnabled,
                onCheckedChange = { dynamicColorEnabled = it }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text("Hakkında", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))

        // 3. Uygulama Sürümü Bilgisi
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Info, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("DashNote Sürümü", style = MaterialTheme.typography.bodyLarge)
                Text("v1.0.0-alpha", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}