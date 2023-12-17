package com.example.bookshelf.network

import com.example.example.Book
import com.example.example.Items
import retrofit2.http.GET
import retrofit2.http.Query

interface BookshelfApiService {
    @GET("books/v1/volumes")
    suspend fun getUrls(@Query("q") q: String): Book
}