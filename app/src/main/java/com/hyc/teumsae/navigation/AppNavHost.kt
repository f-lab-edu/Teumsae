package com.hyc.teumsae.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hyc.teumsae.home.HomeScreen
import com.hyc.teumsae.onboarding.screen.FirstSetupScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = OnBoardingRoute) {
        composable<HomeRoute> {
            HomeScreen(onNavigationToOnBoarding = { navController.navigate(OnBoardingRoute) })
        }
        composable<OnBoardingRoute> {
            FirstSetupScreen(onNavigateToHome = { navController.navigate(HomeRoute) })
        }
    }
}