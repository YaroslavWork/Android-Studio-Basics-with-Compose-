/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.dessertclicker.model

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.data.DessertUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * [Dessert] is the data class to represent the Dessert imageId, price, and startProductionAmount
 */
class DessertViewModel: ViewModel() {
    // Game UI State
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    // Game State
    private var _dessertSold = 0
    val dessertSold: Int
        get() = _dessertSold

    private var _revenue = 0
    val revenue: Int
        get() = _revenue

    private var _curDessertId: Int = 0
    val curDessertId: Int
        get() = _curDessertId

    @DrawableRes private var _curDessertResId: Int = 0
    val curDessertResId: Int
        get() = _curDessertResId

    init {
        resetApp()
    }

    private fun resetApp() {
        _dessertSold = 0
        _revenue = 0
        _curDessertId = 0
        _curDessertResId = Datasource.dessertList[0].imageId
        _uiState.value = DessertUiState()
    }

    fun updateAppState() {

        _uiState.value = DessertUiState(
            dessertSold = _dessertSold++,
            revenue = _revenue++,
        )
    }

    fun getNextDessert(): Int {
        if (Datasource.dessertList[_curDessertResId].startProductionAmount <= _dessertSold) {
            return _curDessertResId + 1
        } else {
            return _curDessertResId
        }
    }
}
