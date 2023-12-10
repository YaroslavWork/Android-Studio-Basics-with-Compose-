package com.example.wifimonitor

import android.app.Application
import com.example.wifimonitor.data.AppContainer
import com.example.wifimonitor.data.AppDataContainer

class WifiMonitorApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}