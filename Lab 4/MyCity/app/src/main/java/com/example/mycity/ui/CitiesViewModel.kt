package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.LocalCitiesDataProvider
import com.example.mycity.model.City
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class CitiesUiState(
    val currentCity: City = LocalCitiesDataProvider.defaultCity,
)
class CitiesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        CitiesUiState(
            currentCity = LocalCitiesDataProvider.defaultCity,
        )
    )
    val uiState: StateFlow<CitiesUiState> = _uiState

    fun updateCurrentCity(selectedCity: City) {
        _uiState.update {
            it.copy(currentCity = selectedCity)
        }
    }
}