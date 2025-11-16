package com.ottapp.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Search : Screen("search")
    object Library : Screen("library")
    object Profile : Screen("profile")
}
