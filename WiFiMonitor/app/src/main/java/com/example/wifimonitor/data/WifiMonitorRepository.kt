package com.example.wifimonitor.data

import kotlinx.coroutines.flow.Flow

interface WifiMonitorRepository {
    fun getLastItemsStream(amount: Int = 1): Flow<List<Wifi>>

    fun getLastItemsByBssidStream(bssid: String, amount: Int = 1): Flow<List<Wifi>>

    fun getLastActiveItemsStream(amount: Int = 1): Flow<List<Wifi>>

    suspend fun insertItem(wifi: Wifi)

    suspend fun deleteItem(wifi: Wifi)

    suspend fun deleteAllItems()

    suspend fun updateItem(wifi: Wifi)

}