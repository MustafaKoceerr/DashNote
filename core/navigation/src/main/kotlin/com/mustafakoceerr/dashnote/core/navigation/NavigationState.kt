package com.mustafakoceerr.dashnote.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack

@Composable
fun rememberNavigationState(
    startKey: NavKey,
    topLevelKeys: Set<NavKey>,
): NavigationState {
    val topLevelStack = rememberNavBackStack(startKey)
    val subStacks = topLevelKeys.associateWith { key -> rememberNavBackStack(key) }

    return remember(startKey, topLevelKeys) {
        NavigationState(
            startKey = startKey,
            topLevelStack = topLevelStack,
            subStacks = subStacks,
        )
    }
}

class NavigationState(
    val startKey: NavKey,
    val topLevelStack: NavBackStack<NavKey>,
    val subStacks: Map<NavKey, NavBackStack<NavKey>>,
) {
    val currentTopLevelKey: NavKey by derivedStateOf { topLevelStack.last() }

    val topLevelKeys
        get() = subStacks.keys

    val currentSubStack: NavBackStack<NavKey>
        get() = subStacks[currentTopLevelKey]
            ?: error("$currentTopLevelKey için alt yığın (Sub stack) bulunamadı")

    val currentKey: NavKey by derivedStateOf { currentSubStack.last() }
}

/**
 * [Nav3 1.1.0 Güncellemesi]: Decorator'lar (ViewModelStore vb.) artık NavDisplay seviyesinde
 * SceneDecoratorStrategy ile yönetildiği için, burada sadece yığınları (stacks)
 * düz bir NavEntry listesine çeviriyoruz. Amelelik yok, temiz kod var.
 */
@Composable
fun NavigationState.toEntries(
    entryProvider: (NavKey) -> NavEntry<NavKey>,
): SnapshotStateList<NavEntry<NavKey>> {
    return topLevelStack
        .flatMap { topLevelKey ->
            // Aktif Top-Level rotanın altındaki tüm yığını (SubStack) alıyoruz
            val stack = subStacks[topLevelKey] ?: emptyList()
            // Her bir anahtarı EntryProvider üzerinden NavEntry'e dönüştürüyoruz
            stack.map { key -> entryProvider(key) }
        }
        .toMutableStateList()
}