package com.hyc.teumsae

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.hyc.teumsae.design_system.theme.TeumsaeTheme
import com.hyc.teumsae.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TeumsaeTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}