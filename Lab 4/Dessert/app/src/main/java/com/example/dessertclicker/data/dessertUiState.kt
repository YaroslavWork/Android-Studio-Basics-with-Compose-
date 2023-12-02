package com.example.dessertclicker.data

import androidx.annotation.DrawableRes

data class DessertUiState (
    val curDesertIndex: Int = 0,
    val dessertSold: Int = 0,
    val revenue: Int = 0,
    val curDessertPrice: Int = Datasource.dessertList[curDesertIndex].price,
    @DrawableRes val curDessertResId: Int = Datasource.dessertList[curDesertIndex].imageId,
)
