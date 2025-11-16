package com.ottapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ottapp.presentation.home.HomeScreen
import com.ottapp.presentation.home.HomeViewModel
import com.ottapp.presentation.library.LibraryScreen
import com.ottapp.presentation.profile.ProfileScreen
import com.ottapp.presentation.search.SearchScreen
import com.ottapp.presentation.splash.SplashScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(viewModel = homeViewModel)
        }

        composable(Screen.Search.route) {
            SearchScreen()
        }

        composable(Screen.Library.route) {
            LibraryScreen()
        }

        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}
