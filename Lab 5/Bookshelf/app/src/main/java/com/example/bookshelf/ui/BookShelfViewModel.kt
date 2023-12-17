package com.example.bookshelf.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BookshelfRepository
import com.example.example.Book
import com.example.example.Items
import kotlinx.coroutines.launch
import retrofit2.http.Query

sealed class BookShelfUiState {
    object Loading: BookShelfUiState()
    data class Success(val items: Book): BookShelfUiState()
    data class Error(val message: String): BookShelfUiState()
}

class BookShelfViewModel(
    private val bookShelfRepository: BookshelfRepository,
) : ViewModel() {
    var bookShelfUiState: BookShelfUiState by mutableStateOf(BookShelfUiState.Loading)
        private set

    fun getBooks(query: String) {
        viewModelScope.launch {
            bookShelfUiState = BookShelfUiState.Loading
            bookShelfUiState = try {
                BookShelfUiState.Success(bookShelfRepository.getUrls(query))
            } catch (e: Exception) {
                BookShelfUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as BookshelfApplication)
                val bookShelfRepository = application.container.bookshelfRepository
                BookShelfViewModel(bookShelfRepository = bookShelfRepository)
            }
        }
    }
}