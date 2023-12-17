package com.example.wifimonitor.ui.screens


import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wifimonitor.R
import com.example.wifimonitor.WifiMonitorTopAppBar
import com.example.wifimonitor.ui.AppViewModelProvider
import com.example.wifimonitor.ui.navigation.NavigationDestination
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

object HomeDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToInformationList: () -> Unit,
) {
    val viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val homeUiState by viewModel.homeUiState.collectAsState()
    val estimatedDistanceUiState by viewModel.estimatedDistanceUiState.collectAsState()
    Scaffold(
        topBar = {
            WifiMonitorTopAppBar(
                title = stringResource(id = HomeDestination.titleRes),
                canNavigateBack = false,
                navigateUp = { },
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = innerPadding
        ) {
            item {
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
                    Text(
                        text = stringResource(R.string.button_check_wi_fi_data),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            item {
                Text(
                    text = stringResource(R.string.title_active_wi_fi),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                ActiveWifiCard(
                    homeUiState = homeUiState,
                    estimatedDistanceUiState = estimatedDistanceUiState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Text(
                    text = stringResource(R.string.title_nearby_wi_fi, homeUiState.nearbyWifi.size),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            items(homeUiState.nearbyWifi) { wifi ->
                NearbyWifiCard(
                    ssidString = wifi.ssid,
                    bssidString = wifi.bssid ?: "",
                    estimatedDistanceString = wifi.estimatedDistance.toString(),
                    rssiString = wifi.rssi.toString(),
                    frequencyString = wifi.frequency.toString(),
                    recordTimeString = wifi.recordTime,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ActiveWifiCard(
    homeUiState: HomeUiState,
    estimatedDistanceUiState: EstimatedDistanceUiState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = homeUiState.activeWifi.ssid,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = homeUiState.activeWifi.recordTime,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            if (homeUiState.isConnecting) {
                if (estimatedDistanceUiState.activeMinEstimatedDistance == estimatedDistanceUiState.activeMaxEstimatedDistance) {
                    InformationRow(
                        title = stringResource(id = R.string.title_estimated_distance),
                        information = "${estimatedDistanceUiState.activeMinEstimatedDistance} m"
                    )
                } else {
                    InformationRow(
                        title = stringResource(id = R.string.title_estimated_distance),
                        information = "${estimatedDistanceUiState.activeMinEstimatedDistance} m - ${estimatedDistanceUiState.activeMaxEstimatedDistance} m"
                    )
                }
                InformationRow(
                    title = stringResource(id = R.string.title_ssid),
                    information = homeUiState.activeWifi.ssid
                )
                InformationRow(
                    title = stringResource(id = R.string.title_bssid),
                    information = homeUiState.activeWifi.bssid ?: ""
                )
                InformationRow(
                    title = stringResource(id = R.string.title_link_speed),
                    information = "${homeUiState.activeWifi.linkSpeed} Mbps"
                )
                InformationRow(
                    title = stringResource(id = R.string.title_rssi),
                    information = "${homeUiState.activeWifi.rssi} dBm"
                )
                InformationRow(
                    title = stringResource(id = R.string.title_frequency),
                    information = "${homeUiState.activeWifi.frequency} MHz"
                )

            } else {
                Text(text = stringResource(R.string.info_no_active_wifi_found))
            }
        }
    }
}

@Composable
fun NearbyWifiCard(
    ssidString: String,
    bssidString: String,
    estimatedDistanceString: String,
    rssiString: String,
    frequencyString: String,
    recordTimeString: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = ssidString,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = recordTimeString,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            Column() {
                InformationRow(
                    title = stringResource(id = R.string.title_ssid),
                    information = ssidString,
                )
                InformationRow(
                    title = stringResource(id = R.string.title_bssid),
                    information = bssidString
                )
                InformationRow(
                    title = stringResource(id = R.string.title_estimated_distance),
                    information = "$estimatedDistanceString m"
                )
                InformationRow(
                    title = stringResource(id = R.string.title_rssi),
                    information = "$rssiString dBm"
                )
                InformationRow(
                    title = stringResource(id = R.string.title_frequency),
                    information = "$frequencyString MHz"
                )
            }
        }
    }
}

@Composable
fun InformationRow(
    title: String,
    information: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .padding(dimensionResource(id = R.dimen.padding_very_small))
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,

            )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = information,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}