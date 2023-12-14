package com.example.wifimonitor.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val uiState: StateFlow<WifiInformationUiState> =
        wifiMonitorRepository.getLastActiveItemsStream(10)
            .filterNotNull()
            .map { wifiList ->
                WifiInformationUiState(wifiStates = wifiList)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = WifiInformationUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}


data class WifiInformationUiState(
    var wifiStates: List<Wifi> = emptyList(),
)