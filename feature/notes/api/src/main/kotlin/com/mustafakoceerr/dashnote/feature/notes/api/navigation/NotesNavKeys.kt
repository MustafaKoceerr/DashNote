package com.mustafakoceerr.dashnote.feature.notes.api.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

// Top-Level Rotalar (Drawer'da görünecekler)
@Serializable
object NotesRoute : NavKey

@Serializable
object ArchiveRoute : NavKey

@Serializable
object TrashRoute : NavKey

// Sub-Level Rota (Bir nota tıklandığında açılacak Editör)
@Serializable
data class EditorRoute(val noteId: String? = null) :
    NavKey // null ise yeni not, doluysa var olan not