package com.example.wifimonitor.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wifimonitor.ui.screens.HomeDestination
import com.example.wifimonitor.ui.screens.HomeScreen
import com.example.wifimonitor.ui.screens.HomeViewModel

@Composable
fun WifiMonitorNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val homeViewModel = HomeViewModel(context = LocalContext.current)

    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(viewModel = homeViewModel)
        }
    }
}