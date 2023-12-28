package com.example.wifimonitor

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.wifimonitor.data.AppContainer
import com.example.wifimonitor.data.AppDataContainer
import kotlin.reflect.KClass

//@HiltAndroidApp
class WifiMonitorApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}