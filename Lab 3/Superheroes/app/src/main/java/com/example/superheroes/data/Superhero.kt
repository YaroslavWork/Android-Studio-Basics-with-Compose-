package com.example.superheroes.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.superheroes.R

data class Superhero(
    @DrawableRes val imageResourceId: Int,
    @StringRes val nickname: Int,
    @StringRes val slogan: Int
)

object HeroesRepository {
    val heroes = listOf(
        Superhero(
            nickname = R.string.hero1,
            slogan = R.string.description1,
            imageResourceId = R.drawable.android_superhero1
        ),
        Superhero(
            nickname = R.string.hero2,
            slogan = R.string.description2,
            imageResourceId = R.drawable.android_superhero2
        ),
        Superhero(
            nickname = R.string.hero3,
            slogan = R.string.description3,
            imageResourceId = R.drawable.android_superhero3
        ),
        Superhero(
            nickname = R.string.hero4,
            slogan = R.string.description4,
            imageResourceId = R.drawable.android_superhero4
        ),
        Superhero(
            nickname = R.string.hero5,
            slogan = R.string.description5,
            imageResourceId = R.drawable.android_superhero5
        ),
        Superhero(
            nickname = R.string.hero6,
            slogan = R.string.description6,
            imageResourceId = R.drawable.android_superhero6
        )
    )
}

