package com.example.wifimonitor.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import com.example.wifimonitor.data.Wifi
import com.example.wifimonitor.data.WifiMonitorRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InformationViewModel(
    private val wifiMonitorRepository: WifiMonitorRepository
) : ViewModel() {
    val lastAmount = 25
    val maxShowInSeconds = 120

    val uiState: StateFlow<WifiInformationUiState> =
        wifiMonitorRepository.getLastActiveItemsStream(lastAmount)
            .filterNotNull()
            .map { wifiList ->
                updateGraph(wifiList)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = WifiInformationUiState()
            )

    private fun updateGraph(wifiList: List<Wifi>): WifiInformationUiState {
        val pointsData: MutableList<Point> = mutableListOf()

        for (wifi in wifiList) {
            val timeAgo = (System.currentTimeMillis() - wifi.timestamp) / 1000
            if (timeAgo > maxShowInSeconds) {
                continue
            }
            pointsData.add(Point(((System.currentTimeMillis()-wifi.timestamp)/1000).toFloat(), wifi.rssi.toFloat()))
        }

        return WifiInformationUiState(wifiStates = wifiList, pointsData = pointsData)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}


data class WifiInformationUiState(
    var wifiStates: List<Wifi> = emptyList(),
    var pointsData: List<Point> = emptyList()
)