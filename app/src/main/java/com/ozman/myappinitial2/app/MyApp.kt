package com.ozman.myappinitial2.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Main Application class annotated with @HiltAndroidApp to trigger Hilt's code generation.
 * This class serves as the entry point of the app where Hilt components are generated and can be injected.
 */
@HiltAndroidApp
class MyApp : Application() {
}