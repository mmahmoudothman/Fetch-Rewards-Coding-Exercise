package com.ozman.myappinitial2.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ozman.myappinitial2.presentation.main.ItemListScreen
import com.ozman.myappinitial2.presentation.splash.SplashScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(onTimeOut = {
                navController.navigate("main") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        composable("main") {
            ItemListScreen()
        }
    }
}

