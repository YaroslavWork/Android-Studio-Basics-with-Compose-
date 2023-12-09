package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BookshelfApiService

interface BookshelfRepository {
    suspend fun getUrls(query: String): String
}

class NetworkBookshelfRepository(
    private val bookshelfApiService: BookshelfApiService,
) : BookshelfRepository {

    override suspend fun getUrls(query: String): String {
        return bookshelfApiService.getUrls(query)
    }
}