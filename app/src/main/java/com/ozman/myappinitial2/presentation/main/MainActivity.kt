package com.ozman.myappinitial2.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ozman.myappinitial2.nav.AppNavHost
import com.ozman.myappinitial2.ui.theme.MyAppInitial2Theme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity setting up the content and navigation for the app.
 * Uses Dagger Hilt for dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppInitial2Theme {
                AppNavHost()
            }
        }
    }
}