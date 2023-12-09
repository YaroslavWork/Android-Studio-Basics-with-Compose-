package com.example.bookshelf.network

import com.example.bookshelf.model.Book
import retrofit2.http.GET
import retrofit2.http.Query

interface BookshelfApiService {
    @GET("books/v1/volumes")
    suspend fun getUrls(@Query("q") q: String): String
}