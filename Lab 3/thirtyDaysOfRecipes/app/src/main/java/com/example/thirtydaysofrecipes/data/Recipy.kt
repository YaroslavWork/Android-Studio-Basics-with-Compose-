package com.example.thirtydaysofrecipes.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.thirtydaysofrecipes.R

data class Recipy(
    val id: Int = 0,
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
)

val recipes = listOf(
    Recipy(
        id = 1,
        name = R.string.title_1,
        description = R.string.description_1,
        imageResourceId = R.drawable.recipe_1
    ),
    Recipy(
        id = 2,
        name = R.string.title_2,
        description = R.string.description_2,
        imageResourceId = R.drawable.recipe_2
    ),
    Recipy(
        id = 3,
        name = R.string.title_3,
        description = R.string.description_3,
        imageResourceId = R.drawable.recipe_3
    ),
    Recipy(
        id = 4,
        name = R.string.title_4,
        description = R.string.description_4,
        imageResourceId = R.drawable.recipe_4
    ),
    Recipy(
        id = 5,
        name = R.string.title_5,
        description = R.string.description_5,
        imageResourceId = R.drawable.recipe_3
    ),
    Recipy(
        id = 6,
        name = R.string.title_6,
        description = R.string.description_6,
        imageResourceId = R.drawable.recipe_2
    ),
    Recipy(
        id = 7,
        name = R.string.title_7,
        description = R.string.description_7,
        imageResourceId = R.drawable.recipe_1
    ),
    Recipy(
        id = 8,
        name = R.string.title_8,
        description = R.string.description_8,
        imageResourceId = R.drawable.recipe_2
    ),
    Recipy(
        id = 9,
        name = R.string.title_9,
        description = R.string.description_9,
        imageResourceId = R.drawable.recipe_3
    ),
    Recipy(
        id = 10,
        name = R.string.title_10,
        description = R.string.description_10,
        imageResourceId = R.drawable.recipe_4
    ),
    Recipy(
        id = 11,
        name = R.string.title_11,
        description = R.string.description_11,
        imageResourceId = R.drawable.recipe_3
    ),
    Recipy(
        id = 12,
        name = R.string.title_12,
        description = R.string.description_12,
        imageResourceId = R.drawable.recipe_2
    ),
    Recipy(
        id = 13,
        name = R.string.title_13,
        description = R.string.description_13,
        imageResourceId = R.drawable.recipe_1
    ),
    Recipy(
        id = 14,
        name = R.string.title_14,
        description = R.string.description_14,
        imageResourceId = R.drawable.recipe_2
    ),
    Recipy(
        id = 15,
        name = R.string.title_15,
        description = R.string.description_15,
        imageResourceId = R.drawable.recipe_3
    ),
    Recipy(
        id = 16,
        name = R.string.title_16,
        description = R.string.description_16,
        imageResourceId = R.drawable.recipe_4
    ),
    Recipy(
        id = 17,
        name = R.string.title_17,
        description = R.string.description_17,
        imageResourceId = R.drawable.recipe_3
    ),
    Recipy(
        id = 18,
        name = R.string.title_18,
        description = R.string.description_18,
        imageResourceId = R.drawable.recipe_2
    ),
    Recipy(
        id = 19,
        name = R.string.title_19,
        description = R.string.description_19,
        imageResourceId = R.drawable.recipe_1
    ),
    Recipy(
        id = 20,
        name = R.string.title_20,
        description = R.string.description_20,
        imageResourceId = R.drawable.recipe_2
    ),
    Recipy(
        id = 21,
        name = R.string.title_21,
        description = R.string.description_21,
        imageResourceId = R.drawable.recipe_3
    ),
    Recipy(
        id = 22,
        name = R.string.title_22,
        description = R.string.description_22,
        imageResourceId = R.drawable.recipe_4
    ),
    Recipy(
        id = 23,
        name = R.string.title_23,
        description = R.string.description_23,
        imageResourceId = R.drawable.recipe_3
    ),
    Recipy(
        id = 24,
        name = R.string.title_24,
        description = R.string.description_24,
        imageResourceId = R.drawable.recipe_2
    ),
    Recipy(
        id = 25,
        name = R.string.title_25,
        description = R.string.description_25,
        imageResourceId = R.drawable.recipe_1
    ),
    Recipy(
        id = 26,
        name = R.string.title_26,
        description = R.string.description_26,
        imageResourceId = R.drawable.recipe_2
    ),
    Recipy(
        id = 27,
        name = R.string.title_27,
        description = R.string.description_27,
        imageResourceId = R.drawable.recipe_3
    ),
    Recipy(
        id = 28,
        name = R.string.title_28,
        description = R.string.description_28,
        imageResourceId = R.drawable.recipe_4
    ),
    Recipy(
        id = 29,
        name = R.string.title_29,
        description = R.string.description_29,
        imageResourceId = R.drawable.recipe_3
    ),
    Recipy(
        id = 30,
        name = R.string.title_30,
        description = R.string.description_30,
        imageResourceId = R.drawable.recipe_2
    ),
)