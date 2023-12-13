package com.example.wifimonitor.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wifimonitor.R
import com.example.wifimonitor.WifiMonitorTopAppBar
import com.example.wifimonitor.ui.AppViewModelProvider
import com.example.wifimonitor.ui.navigation.NavigationDestination
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope

object WifiInformationDestination : NavigationDestination {
    override val route: String = "information"
    override val titleRes: Int = R.string.wifi_information
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WifiInformationScreen(
    navigateBack: () -> Unit,
    viewModel: InformationViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    //val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            WifiMonitorTopAppBar(
                title = stringResource(id = WifiInformationDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) {
//        for (information in uiState.value.wifiStates) {
//            Text(text = information.ssid)
//        }
    }
}