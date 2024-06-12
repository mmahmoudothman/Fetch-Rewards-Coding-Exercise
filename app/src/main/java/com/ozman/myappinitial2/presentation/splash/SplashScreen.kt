package com.ozman.myappinitial2.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ozman.myappinitial2.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(onTimeOut: () -> Unit) {
    var startMainScreen by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(3000)
        startMainScreen = true
        onTimeOut()
    }
    if (!startMainScreen) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.fetch),
                contentDescription = stringResource(id = R.string.fetch_name)
            )
        }
    }
}

