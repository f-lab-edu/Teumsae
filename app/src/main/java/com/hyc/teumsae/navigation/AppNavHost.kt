package com.hyc.teumsae.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hyc.teumsae.home.HomeScreen
import com.hyc.teumsae.onboarding.presentation.OnBoardingScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(onNavigationToOnBoarding = { navController.navigate(OnBoardingRoute) })
        }
        composable<OnBoardingRoute> {
            OnBoardingScreen(onNavigationToHome = { navController.navigate(HomeRoute) })
        }
    }
}