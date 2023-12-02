package com.example.dessertclicker.data

import androidx.annotation.DrawableRes

data class DessertUiState (
    val curDessertIndex: Int = 0,
    val dessertSold: Int = 0,
    val revenue: Int = 0,
    val curDessertPrice: Int = Datasource.dessertList[curDessertIndex].price,
    @DrawableRes val curDessertResId: Int = Datasource.dessertList[curDessertIndex].imageId,
)
