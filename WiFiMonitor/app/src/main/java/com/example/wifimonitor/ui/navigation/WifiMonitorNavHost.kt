package com.example.wifimonitor.ui.navigation

import android.text.Spannable.Factory
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wifimonitor.WifiMonitorApplication
import com.example.wifimonitor.ui.screens.HomeDestination
import com.example.wifimonitor.ui.screens.HomeScreen
import com.example.wifimonitor.ui.screens.HomeViewModel
import com.example.wifimonitor.ui.screens.InformationViewModel
import com.example.wifimonitor.ui.screens.WifiInformationDestination
import com.example.wifimonitor.ui.screens.WifiInformationScreen

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
            HomeScreen(
                viewModel = homeViewModel,
                navigateToInformationList = { navController.navigate(WifiInformationDestination.route) }
            )
        }
        composable(route = WifiInformationDestination.route) {
            WifiInformationScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}