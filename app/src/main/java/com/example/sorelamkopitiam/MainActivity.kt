package com.example.sorelamkopitiam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import com.example.sorelamkopitiam.presentation.navigation.AppNavigation
import com.example.sorelamkopitiam.ui.theme.SorelamKopitiamTheme

@AndroidEntryPoint // ✅ Penting untuk Hilt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SorelamKopitiamTheme {
                AppNavigation() // ✅ Berisi NavHost + Routing
            }
        }
    }
}
