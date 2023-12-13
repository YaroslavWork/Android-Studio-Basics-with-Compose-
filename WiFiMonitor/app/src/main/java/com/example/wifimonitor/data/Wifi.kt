package com.example.wifimonitor.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.log10

@Entity(tableName = "wifi")
data class Wifi(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val ssid: String = "",
    val bssid: String? = "",
    val rssi: Int = -1,
    val frequency: Int? = -1,
    val linkSpeed: Int = 0,
    val estimatedDistance: Double = calculateDistance(rssi, frequency ?: -1),
    val timestamp: Long = System.currentTimeMillis(),
    val recordTime: String = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(
        timestamp
    )
)

// Tłumienie w swobodnej przestrzeni
private fun calculateDistance(rssi: Int, frequency: Int): Double {
    if (frequency == -1 || rssi == -1) {
        return -1.0
    }

    val exp = 10.0.pow((abs(rssi) + 27.55 - 20 * log10(frequency.toDouble())) / 20)
    return DecimalFormat("#.##").format(exp).toDouble()
}
