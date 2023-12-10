package com.example.wifimonitor.data

import android.content.Context

interface AppContainer {
    val wifiMonitorRepository: WifiMonitorRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val wifiMonitorRepository: WifiMonitorRepository
        get() = TODO("Not yet implemented")
}