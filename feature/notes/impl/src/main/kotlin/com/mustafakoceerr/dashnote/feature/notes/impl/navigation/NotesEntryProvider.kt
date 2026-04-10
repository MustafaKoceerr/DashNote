package com.mustafakoceerr.dashnote.feature.notes.impl.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.mustafakoceerr.dashnote.core.navigation.Navigator
import com.mustafakoceerr.dashnote.feature.notes.api.navigation.ArchiveRoute
import com.mustafakoceerr.dashnote.feature.notes.api.navigation.EditorRoute
import com.mustafakoceerr.dashnote.feature.notes.api.navigation.NotesRoute
import com.mustafakoceerr.dashnote.feature.notes.api.navigation.TrashRoute

fun EntryProviderScope<NavKey>.notesEntries(navigator: Navigator) {

    entry<NotesRoute> {
        // Temsili bir liste görünümü
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(5) { index ->
                Card(
                    onClick = { navigator.navigate(EditorRoute("not_$index")) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Not Başlığı $index", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "Bu notun içeriği burada görünecek...",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }

    entry<ArchiveRoute> { DummyScreen("Arşivlenmiş Notlarınız") }
    entry<TrashRoute> { DummyScreen("Çöp Kutusundaki Notlarınız") }

    entry<EditorRoute> { route ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Başlık") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Notunuzu yazın...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@androidx.compose.runtime.Composable
private fun DummyScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}