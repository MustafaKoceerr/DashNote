package com.mustafakoceerr.dashnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mustafakoceerr.dashnote.core.designsystem.theme.DashNoteTheme
import com.mustafakoceerr.dashnote.ui.DashNoteApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashNoteTheme {
                DashNoteApp()
            }
        }
    }
}


