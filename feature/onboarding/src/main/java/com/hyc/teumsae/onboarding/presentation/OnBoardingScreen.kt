package com.hyc.teumsae.onboarding.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OnBoardingScreen(onNavigationToHome: () -> Unit) {
    Scaffold() { it
        Column() {
            Text("OnBoarding Screen")
            Button(onClick = onNavigationToHome) {
                Text("Go to Home")
            }
        }
    }
}