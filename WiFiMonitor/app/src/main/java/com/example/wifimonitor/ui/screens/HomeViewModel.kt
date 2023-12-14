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
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.toList
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow


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

// Tłumienie w swobodnej przestrzeni
private fun calculateDistance(rssi: Int, frequency: Int): Double {
    if (frequency == -1 || rssi == -1) {
        return -1.0
    }

    val exp = 10.0.pow((abs(rssi) + 27.55 - 20 * log10(frequency.toDouble())) / 20)
    return DecimalFormat("#.##").format(exp).toDouble()
}

class HomeViewModel (
    private val application: WifiMonitorApplication,
    private val wifiMonitorRepository: WifiMonitorRepository
) : ViewModel() {
    private val TIME_DELAY = 5000L

    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: MutableStateFlow<HomeUiState> = _homeUiState

    private val wifiManager = ContextCompat.getSystemService(context, WifiManager::class.java)

    private val receiver = WifiScanReceiver {
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


                val activeWifi: Wifi = Wifi(
                    ssid = activeWifiInfo?.ssid ?: "",
                    bssid = activeWifiInfo?.bssid ?: "",
                    rssi = activeWifiInfo?.rssi ?: -1,
                    frequency = activeWifiInfo?.frequency ?: -1,
                    linkSpeed = activeWifiInfo?.linkSpeed ?: -1,
                    estimatedDistance = calculateDistance(activeWifiInfo?.rssi ?: -1, activeWifiInfo?.frequency ?: -1),
                    isActive = true
                )
                val nearbyWifi: List<Wifi> = wifiScanResults.map {
                    Wifi(
                        ssid = it.SSID,
                        bssid = it.BSSID,
                        rssi = it.level,
                        frequency = it.frequency,
                        estimatedDistance = calculateDistance(it.level, it.frequency),
                        isActive = false
                        )
                }
                val isConnecting = wifiManager?.isWifiEnabled ?: false

                if (isConnecting) {
                    wifiMonitorRepository.insertItem(activeWifi)
                }
                for (wifi in nearbyWifi) {
                    wifiMonitorRepository.insertItem(wifi)
                }

                val updatedUiState = HomeUiState(
                    activeWifi = activeWifi,
                    nearbyWifi = nearbyWifi,
                    isConnecting = isConnecting
                )

                _homeUiState.value = updatedUiState

                delay(TIME_DELAY)
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
    val nearbyWifi: List<Wifi> = emptyList(),
    val isConnecting: Boolean = false,
)