package com.example.bookshelf.ui.screens

import android.widget.EditText
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.BookShelfUiState
import com.example.bookshelf.ui.BookShelfViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen()
{
    val bookshelfViewModel: BookShelfViewModel = viewModel(factory = BookShelfViewModel.Factory)
    val bookShelfUiState: BookShelfUiState = bookshelfViewModel.bookShelfUiState

    var text by remember { mutableStateOf("") }
    Column(

    ) {
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(55.dp)
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Search a book...") },
                modifier = Modifier
                    .weight(4f)
                    .fillMaxSize()
                    .padding(end = 8.dp)
            )
            Button(
                onClick = { bookshelfViewModel.getBooks(text) },
                enabled = text.isNotEmpty(),
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize()
            ) {
                Text("Search")
            }
        }
        when(
            bookShelfUiState
        ) {
            is BookShelfUiState.Loading -> {
                Text("Loading...")
            }
            is BookShelfUiState.Success -> {
                BookListScreen()
            }
            is BookShelfUiState.Error -> {
                Text("Error: " + bookShelfUiState.message)
            }
        }
    }
}

@Composable
fun BookListScreen(

) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),

    ) {
        items(15) {
            BookItem()
        }
    }
}

@Composable
fun BookItem(

) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(55.dp)
    ) {
        Text("Book title")
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun BookListScreenPreview() {
    BookListScreen()
}

@Preview(showBackground = true)
@Composable
fun BookItemPreview() {
    BookItem()
}
