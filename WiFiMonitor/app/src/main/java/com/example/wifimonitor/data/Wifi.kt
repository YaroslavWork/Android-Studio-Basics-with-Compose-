package com.example.wifimonitor.data

data class Wifi(
    val ssid: String = "",
    val bssid: String? = "",
    val rssi: Int = -1,
    val frequency: Int? = -1,
    val linkSpeed: Number = -1.0f
)
