package com.example.wifimonitor.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wifimonitor.R
import com.example.wifimonitor.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    Column(

    ) {
        Text(
            text = "SSID: ${homeUiState.activeWifi.ssid}\n" +
                    "BSSID: ${homeUiState.activeWifi.bssid}\n" +
                    "RSSI: ${homeUiState.activeWifi.rssi} dBm\n" +
                    "Link Speed: ${homeUiState.activeWifi.linkSpeed} Mbps\n" +
                    "Frequency: ${homeUiState.activeWifi.frequency} MHz\n",
        )

        LazyColumn() {
            if (homeUiState.nearbyWifi.isEmpty()) {
                item {
                    Text(text = "No nearby wifi found")
                }
            } else {
                items(homeUiState.nearbyWifi) { wifi ->
                    Text(
                        text = "SSID: ${wifi.ssid}\n" +
                                "RSSI: ${wifi.rssi} dBm\n"
                    )
                }
            }
        }
    }
}