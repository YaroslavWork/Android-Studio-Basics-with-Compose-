package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class City(
    val id: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val imageResourceId: Int,
    val amountFreeHotels: Int = 0,
)
