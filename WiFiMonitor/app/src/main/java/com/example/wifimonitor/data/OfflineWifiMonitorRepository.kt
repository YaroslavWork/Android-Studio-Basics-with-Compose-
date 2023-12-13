package com.example.wifimonitor.data

import kotlinx.coroutines.flow.Flow

class OfflineWifiMonitorRepository(
    private val wifiMonitorDao: WifiMonitorDao
) : WifiMonitorRepository {
    override fun getLastItemsStream(amount: Int): Flow<List<Wifi>> = wifiMonitorDao.getLastItems(amount)

    override suspend fun insertItem(wifi: Wifi) = wifiMonitorDao.insert(wifi)

    override suspend fun deleteItem(wifi: Wifi) = wifiMonitorDao.delete(wifi)

    override suspend fun deleteAllItems() = wifiMonitorDao.deleteAll()

    override suspend fun updateItem(wifi: Wifi) = wifiMonitorDao.update(wifi)

}