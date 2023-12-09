package com.example.bookshelf.model

data class Book(
    val title: String,
    val authors: List<String>,
    val description: String,
    val imgSrc: String
)
