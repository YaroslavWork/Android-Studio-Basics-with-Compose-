package com.example.wifimonitor.data

import android.content.Context

interface AppContainer {
    val wifiMonitorRepository: WifiMonitorRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val wifiMonitorRepository: WifiMonitorRepository by lazy {
        OfflineWifiMonitorRepository(WifiMonitorDatabase.getDatabase(context).wifiMonitorDao())
    }
}