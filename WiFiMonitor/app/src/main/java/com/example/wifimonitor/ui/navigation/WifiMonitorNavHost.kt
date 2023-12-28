package com.example.wifimonitor.ui.navigation

import android.text.Spannable.Factory
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wifimonitor.ui.screens.HomeDestination
import com.example.wifimonitor.ui.screens.HomeScreen
import com.example.wifimonitor.ui.screens.PermissionsDestination
import com.example.wifimonitor.ui.screens.RequestPermission
import com.example.wifimonitor.ui.screens.WifiInformationDestination
import com.example.wifimonitor.ui.screens.WifiInformationScreen

@Composable
fun WifiMonitorNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    var checkPermission: Boolean by remember { mutableStateOf(false)}

    NavHost(
        navController = navController,
        startDestination = PermissionsDestination.route,
        modifier = modifier
    ) {
        composable(route = PermissionsDestination.route) {
            RequestPermission(
                permission = android.Manifest.permission.ACCESS_FINE_LOCATION,
                navigateToHomeList = { navController.navigate(HomeDestination.route) },
                checkPermission = checkPermission,
                checkPermissionAction = { checkPermission = true },
            )
        }
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToInformationList = { navController.navigate(WifiInformationDestination.route) }
            )
        }
        composable(route = WifiInformationDestination.route) {
            WifiInformationScreen(
                navigateBack = { navController.navigate(PermissionsDestination.route) }
            )
        }
    }
}



