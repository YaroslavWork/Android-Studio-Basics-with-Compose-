package com.example.wifimonitor.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.sql.Timestamp

@Dao
interface WifiMonitorDao {
    @Query("SELECT * from wifi ORDER BY timestamp DESC LIMIT :amount")
    fun getLastItems(amount: Int = 1): Flow<List<Wifi>>

    @Query("SELECT * from wifi WHERE bssid = :bssid ORDER BY timestamp DESC LIMIT :amount")
    fun getLastItemsByBssid(bssid: String, amount: Int = 1): Flow<List<Wifi>>

    @Query("SELECT * from wifi WHERE isActive = 1 ORDER BY timestamp DESC LIMIT :amount")
    fun getLastActiveItems(amount: Int = 1): Flow<List<Wifi>>

    @Query("SELECT * from wifi WHERE isActive = 1 AND timestamp > :timestamp ORDER BY timestamp DESC")
    fun getLastActiveItemsBySeconds(timestamp: Long): Flow<List<Wifi>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wifi: Wifi)

    @Delete
    suspend fun delete(wifi: Wifi)

    @Query("DELETE FROM wifi")
    suspend fun deleteAll()

    @Update
    suspend fun update(wifi: Wifi)

}