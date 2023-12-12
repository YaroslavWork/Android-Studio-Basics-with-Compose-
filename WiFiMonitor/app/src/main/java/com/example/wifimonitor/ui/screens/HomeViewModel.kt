package com.example.wifimonitor.ui.screens

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifimonitor.WifiMonitorApplication
import com.example.wifimonitor.data.Wifi
import com.example.wifimonitor.data.WifiMonitorRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import android.util.Log


class WifiScanReceiver(private val onScanResult: (List<ScanResult>) -> Unit) : BroadcastReceiver() {
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) {
            val wifiManager =
                context?.getSystemService(Context.WIFI_SERVICE) as? WifiManager
            val scanResults = wifiManager?.scanResults ?: emptyList()
            onScanResult(scanResults)
        }
    }
}

class HomeViewModel (context: Context) : ViewModel() {
    val context = context
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: MutableStateFlow<HomeUiState> = _homeUiState

    val wifiManager = ContextCompat.getSystemService(context, WifiManager::class.java)

    val receiver = WifiScanReceiver {
        wifiScanResults = it
    }

    private var wifiScanResults: List<ScanResult> = emptyList()

    init {
        context.registerReceiver(receiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        updateWifi()
    }


    @SuppressLint("MissingPermission")
    fun updateWifi() {
        viewModelScope.launch {
            while (true) {
                wifiManager?.startScan()
                val activeWifiInfo = wifiManager?.connectionInfo
                val updatedUiState = HomeUiState(
                    activeWifi = Wifi(
                        activeWifiInfo?.ssid ?: "",
                        activeWifiInfo?.bssid ?: "",
                        activeWifiInfo?.rssi ?: 0,
                        activeWifiInfo?.frequency ?: 0,
                        activeWifiInfo?.linkSpeed ?: 0
                    ),
                    nearbyWifi = wifiScanResults.map {
                        Wifi(ssid = it.SSID, rssi = it.level, frequency = it.frequency)
                    }
                )
                Log.d("NearbyWifi", "Nearby wifi: ${wifiScanResults.size}")
                _homeUiState.value = updatedUiState

                delay(10000)
            }
        }
    }

    override fun onCleared() {
        // Anulowanie rejestracji BroadcastReceivera przy zamykaniu ViewModelu
        context.unregisterReceiver(receiver)
        super.onCleared()
    }
}

data class HomeUiState(
    val activeWifi: Wifi = Wifi(),
    val nearbyWifi: List<Wifi> = emptyList()
)