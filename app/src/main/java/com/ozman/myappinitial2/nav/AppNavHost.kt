package com.ozman.myappinitial2.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ozman.myappinitial2.presentation.main.ItemListScreen
import com.ozman.myappinitial2.presentation.splash.SplashScreen

/**
 * Navigation host managing the navigation within the app.
 * It defines the navigation graph and controls the navigation between different composable screens.
 */
@Composable
fun AppNavHost() {
    // Create a NavController to handle navigation within the app
    val navController = rememberNavController()

    // Define the navigation graph with NavHost
    NavHost(navController = navController, startDestination = "splash") {
        // Define the composable for the splash screen
        composable("splash") {
            SplashScreen(onTimeOut = {
                navController.navigate("main") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        // Define the composable for the main item list screen
        composable("main") {
            ItemListScreen()
        }
    }
}