package com.ottapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ottapp.core.network.NetworkModule
import com.ottapp.data.repository.HomeRepository
import com.ottapp.presentation.home.HomeViewModel
import com.ottapp.presentation.navigation.BottomNavigationBar
import com.ottapp.presentation.navigation.NavigationGraph
import com.ottapp.ui.theme.OTTAppTheme

class MainActivity : ComponentActivity() {
    
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize dependencies
        val repository = HomeRepository(NetworkModule.apiService)
        homeViewModel = HomeViewModel(repository)
        
        setContent {
            OTTAppTheme {
                MainScreen(homeViewModel = homeViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavigationGraph(
            navController = navController,
            homeViewModel = homeViewModel,
            modifier = Modifier.padding(paddingValues)
        )
    }
}
