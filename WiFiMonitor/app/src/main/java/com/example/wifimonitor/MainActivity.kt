package com.example.wifimonitor

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wifimonitor.ui.theme.WiFiMonitorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WiFiMonitorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        WifiMonitoring()
                        WifiScanner()
                    }
                }
            }
        }
    }
}


@Composable
fun WifiMonitoring() {
    val context = LocalContext.current
    val wifiManager = ContextCompat.getSystemService(context, WifiManager::class.java)

    var wifiInfo by remember { mutableStateOf<WifiInfo?>(null) }

    LaunchedEffect(wifiManager) {
        val receiver = WifiReceiver {
            wifiInfo = it
        }
        val filter = IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        context.registerReceiver(receiver, filter)

    }

    Column(
    ) {
        Button(onClick = { wifiManager?.startScan() }) {
            Text(text = "Update")
        }
        wifiInfo?.let {
            Text("SSID: ${it.ssid}")
            Text("BSSID: ${it.bssid}")
            Text("Signal Strength: ${it.rssi} dBm")
            Text("Link Speed: ${it.linkSpeed} Mbps")
            Text("Frequency: ${it.frequency} MHz")
        } ?: Text("No WiFi connection")
    }
}

@Composable
fun WifiScanner() {
    val context = LocalContext.current
    val wifiManager = ContextCompat.getSystemService(context, WifiManager::class.java)
    var wifiScanResults by remember { mutableStateOf(emptyList<ScanResult>()) }

    LaunchedEffect(Unit) {
        val receiver = WifiScanReceiver {
            wifiScanResults = it
        }
        val filter = IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        context.registerReceiver(receiver, filter)

        // Start WiFi scan
        context.registerReceiver(receiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        context.registerReceiver(receiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        wifiManager?.startScan()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text("Amount of WiFi networks found: ${wifiScanResults.size}")
        if (wifiScanResults.isNotEmpty()) {
            wifiScanResults.forEach { result ->
                Text("SSID: ${result.SSID}, RSSI: ${result.level} dBm")
            }
        } else {
            Text("No WiFi networks found.")
        }

    }
}

class WifiReceiver(private val onWifiInfoChanged: (WifiInfo) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == WifiManager.NETWORK_STATE_CHANGED_ACTION) {
            val wifiInfo =
                ContextCompat.getSystemService(context!!, WifiManager::class.java)?.connectionInfo
            wifiInfo?.let {
                onWifiInfoChanged(it)
            }
        }
    }
}

class WifiScanReceiver(private val onScanResult: (List<ScanResult>) -> Unit) : BroadcastReceiver() {
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) {
            val wifiManager =
                ContextCompat.getSystemService(context!!, WifiManager::class.java)
            val scanResults = wifiManager?.scanResults ?: emptyList()
            onScanResult(scanResults)
        }
    }
}