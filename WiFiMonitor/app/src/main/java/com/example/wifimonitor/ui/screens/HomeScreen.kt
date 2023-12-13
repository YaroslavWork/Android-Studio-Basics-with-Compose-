package com.example.wifimonitor.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wifimonitor.R
import com.example.wifimonitor.WifiMonitorTopAppBar
import com.example.wifimonitor.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navigateToInformationList: () -> Unit,
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Column {
                WifiMonitorTopAppBar(
                    title = stringResource(id = HomeDestination.titleRes),
                    canNavigateBack = false,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Button(
                    onClick = { navigateToInformationList() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 8.dp
                        )
                ) {
                    Text(text = "Check WiFi Data")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (homeUiState.isConnecting) {
                Text(
                    text = "SSID: ${homeUiState.activeWifi.ssid}\n" +
                            "BSSID: ${homeUiState.activeWifi.bssid}\n" +
                            "RSSI: ${homeUiState.activeWifi.rssi} dBm\n" +
                            "Link Speed: ${homeUiState.activeWifi.linkSpeed} Mbps\n" +
                            "Frequency: ${homeUiState.activeWifi.frequency} MHz\n" +
                            "Estimated Distance: ${homeUiState.activeWifi.estimatedDistance} m\n" +
                            "Record Time: ${homeUiState.activeWifi.recordTime}\n"
                )
            } else {
                Text(text = "No active wifi found")
            }

            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                if (homeUiState.nearbyWifi.isEmpty()) {
                    item {
                        Text(text = "No nearby wifi found")
                    }
                } else {
                    items(homeUiState.nearbyWifi) { wifi ->
                        Text(
                            text = "SSID: ${wifi.ssid}\n" +
                                    "RSSI: ${wifi.rssi} dBm\n" +
                                    "Frequency: ${wifi.frequency} MHz\n" +
                                    "Estimated Distance: ${wifi.estimatedDistance} m\n" +
                                    "Record Time: ${wifi.recordTime}\n",
                        )
                    }
                }
            }
        }
    }
}

