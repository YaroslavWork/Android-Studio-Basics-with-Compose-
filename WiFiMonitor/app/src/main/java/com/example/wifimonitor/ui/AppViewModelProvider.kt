package com.example.wifimonitor.ui

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.wifimonitor.WifiMonitorApplication
import com.example.wifimonitor.ui.screens.InformationViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            InformationViewModel(
                WifiMonitorApplication().container.wifiMonitorRepository
            )
        }
    }
}

fun CreationExtras.WifiMonitorApplication(): WifiMonitorApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WifiMonitorApplication)
