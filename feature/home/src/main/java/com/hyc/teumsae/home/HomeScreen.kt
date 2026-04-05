package com.hyc.teumsae.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(onNavigationToOnBoarding: () -> Unit) {
    Scaffold() { it
        Column() {
            Text("Home Screen")
            Button(onClick = onNavigationToOnBoarding) {
                Text("Go to OnBoarding")
            }
        }
    }
}