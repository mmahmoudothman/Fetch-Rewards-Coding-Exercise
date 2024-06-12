package com.ozman.myappinitial2.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ozman.myappinitial2.R
import kotlinx.coroutines.delay

/**
 * Splash screen composable displaying an image and navigating to the main screen after a delay.
 * @param onTimeOut Callback function to navigate to the main screen.
 */
@Composable
fun SplashScreen(onTimeOut: () -> Unit) {
    // State variable to track if the main screen should be started
    var startMainScreen by remember { mutableStateOf(false) }
    // Launch a coroutine to delay for 3 seconds and then set startMainScreen to true
    LaunchedEffect(Unit) {
        delay(3000)
        startMainScreen = true
        onTimeOut()
    }
    if (!startMainScreen) {
        // Display the splash screen image while waiting
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.fetch),
                contentDescription = null
            )
        }
    }
}