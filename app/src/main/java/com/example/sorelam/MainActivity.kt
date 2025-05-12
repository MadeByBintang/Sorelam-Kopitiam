package com.example.sorelam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sorelam.ui.core.AppEntryPoint
import com.example.sorelam.ui.theme.SorelamKopitiamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SorelamKopitiamTheme {
                AppEntryPoint()
            }
        }
    }
}