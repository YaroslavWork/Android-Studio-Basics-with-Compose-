package com.example.mycity.data

import androidx.compose.ui.res.stringResource
import com.example.mycity.R
import com.example.mycity.model.City

object LocalCitiesDataProvider {
    val defaultCity = getCitiesData()[0]

    fun getCitiesData(): List<City> {
        return listOf(
            City(
                id = 1,
                name = R.string.city_name_1,
                description = R.string.description_1,
                imageResourceId = R.drawable.city,
                amountFreeHotels = 3
            ),
            City(
                id = 2,
                name = R.string.city_name_2,
                description = R.string.description_2,
                imageResourceId = R.drawable.city,
                amountFreeHotels = 1
            ),
            City(
                id = 3,
                name = R.string.city_name_3,
                description = R.string.description_3,
                imageResourceId = com.example.mycity.R.drawable.city,
                amountFreeHotels = 2
            ),
            City(
                id = 4,
                name = R.string.city_name_4,
                description = R.string.description_4,
                imageResourceId = com.example.mycity.R.drawable.city,
                amountFreeHotels = 0
            ),
            City(
                id = 5,
                name = R.string.city_name_5,
                description = R.string.description_5,
                imageResourceId = com.example.mycity.R.drawable.city,
                amountFreeHotels = 1
            ),
        )
    }

    fun getRegions(): List<String> {
        return listOf("Center", "North", "South", "West", "East")
    }
}