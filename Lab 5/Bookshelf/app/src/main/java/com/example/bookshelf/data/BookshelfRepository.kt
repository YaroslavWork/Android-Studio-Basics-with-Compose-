package com.example.bookshelf.data

import com.example.bookshelf.network.BookshelfApiService
import com.example.example.Book
import com.example.example.Items

interface BookshelfRepository {
    suspend fun getUrls(query: String): Book
}

class NetworkBookshelfRepository(
    private val bookshelfApiService: BookshelfApiService,
) : BookshelfRepository {

    override suspend fun getUrls(query: String): Book {
        return bookshelfApiService.getUrls(query)
    }
}