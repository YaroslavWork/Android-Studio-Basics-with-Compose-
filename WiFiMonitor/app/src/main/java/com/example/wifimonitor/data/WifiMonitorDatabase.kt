package com.example.wifimonitor.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Wifi::class], version = 3,  exportSchema = false)
abstract class WifiMonitorDatabase: RoomDatabase() {
    abstract fun wifiMonitorDao(): WifiMonitorDao

    companion object {
        @Volatile
        private var Instance: WifiMonitorDatabase? = null

        fun getDatabase(context: Context): WifiMonitorDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, WifiMonitorDatabase::class.java, "wifi_monitor_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}