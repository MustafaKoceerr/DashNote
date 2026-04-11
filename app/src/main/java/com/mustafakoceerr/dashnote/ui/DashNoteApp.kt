package com.mustafakoceerr.dashnote.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.mustafakoceerr.dashnote.core.designsystem.component.DashNoteTopAppBar
import com.mustafakoceerr.dashnote.core.navigation.Navigator
import com.mustafakoceerr.dashnote.core.navigation.rememberNavigationState
import com.mustafakoceerr.dashnote.core.navigation.toEntries
import com.mustafakoceerr.dashnote.feature.auth.api.navigation.AuthRoute
import com.mustafakoceerr.dashnote.feature.auth.impl.navigation.authEntry
import com.mustafakoceerr.dashnote.feature.notes.api.navigation.ArchiveRoute
import com.mustafakoceerr.dashnote.feature.notes.api.navigation.EditorRoute
import com.mustafakoceerr.dashnote.feature.notes.api.navigation.NotesRoute
import com.mustafakoceerr.dashnote.feature.notes.api.navigation.TrashRoute
import com.mustafakoceerr.dashnote.feature.notes.impl.navigation.notesEntries
import com.mustafakoceerr.dashnote.feature.settings.api.navigation.SettingsRoute
import com.mustafakoceerr.dashnote.feature.settings.impl.navigation.settingsEntry
import kotlinx.coroutines.launch
import androidx.compose.runtime.CompositionLocalProvider
import com.mustafakoceerr.dashnote.core.designsystem.util.LocalSnackbarHostState

@Composable
fun DashNoteApp(modifier: Modifier = Modifier) {
    val navigationState = rememberNavigationState(
        startKey = AuthRoute,
        topLevelKeys = setOf(AuthRoute, NotesRoute, ArchiveRoute, TrashRoute, SettingsRoute)
    )

    val navigator = remember { Navigator(navigationState) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val appEntryProvider = entryProvider<NavKey> {
        authEntry(navigator)
        notesEntries(navigator)
        settingsEntry(navigator)
    }

    val currentKey = navigationState.currentKey
    val isTopLevel = currentKey in navigationState.topLevelKeys
    val isAuthScreen = currentKey == AuthRoute

    // BÜTÜN UYGULAMAYI COMPOSITION LOCAL İLE SARMALIYORUZ
    CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {

        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = !isAuthScreen,
            drawerContent = {
                if (!isAuthScreen) {
                    ModalDrawerSheet {
                        Text(
                            "DashNote",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                        HorizontalDivider()
                        NavigationDrawerItem(
                            label = { Text("Notlar") },
                            selected = navigationState.currentTopLevelKey == NotesRoute,
                            onClick = { navigator.navigate(NotesRoute); scope.launch { drawerState.close() } },
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                        NavigationDrawerItem(
                            label = { Text("Arşiv") },
                            selected = navigationState.currentTopLevelKey == ArchiveRoute,
                            onClick = { navigator.navigate(ArchiveRoute); scope.launch { drawerState.close() } },
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                        NavigationDrawerItem(
                            label = { Text("Çöp Kutusu") },
                            selected = navigationState.currentTopLevelKey == TrashRoute,
                            onClick = { navigator.navigate(TrashRoute); scope.launch { drawerState.close() } },
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                        HorizontalDivider()
                        NavigationDrawerItem(
                            label = { Text("Ayarlar") },
                            selected = navigationState.currentTopLevelKey == SettingsRoute,
                            onClick = { navigator.navigate(SettingsRoute); scope.launch { drawerState.close() } },
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        ) {
            // GLOBAL SCAFFOLD
            Scaffold(
                topBar = {
                    if (!isAuthScreen) {
                        val title = when (currentKey) {
                            NotesRoute -> "Notlar"
                            ArchiveRoute -> "Arşiv"
                            TrashRoute -> "Çöp Kutusu"
                            SettingsRoute -> "Ayarlar"
                            is EditorRoute -> "Notu Düzenle"
                            else -> "DashNote"
                        }
                        DashNoteTopAppBar(
                            title = title,
                            isTopLevel = isTopLevel,
                            onNavigationClick = {
                                if (isTopLevel) scope.launch { drawerState.open() } else navigator.goBack()
                            }
                        )
                    }
                },
                floatingActionButton = {
                    // Sadece Ana Notlar ekranında yeni not ekleme butonu gösterilir
                    if (currentKey == NotesRoute) {
                        FloatingActionButton(onClick = { navigator.navigate(EditorRoute(null)) }) {
                            Icon(Icons.Default.Add, contentDescription = "Yeni Not Ekle")
                        }
                    }
                },
                snackbarHost = { SnackbarHost(snackbarHostState) },
                modifier = modifier.fillMaxSize()
            ) { innerPadding ->
                NavDisplay(
                    entries = navigationState.toEntries(appEntryProvider),
                    onBack = { navigator.goBack() },
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }
        }
    }
}