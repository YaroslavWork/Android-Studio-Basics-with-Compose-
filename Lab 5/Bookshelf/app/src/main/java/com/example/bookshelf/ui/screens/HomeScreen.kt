package com.example.bookshelf.ui.screens

import android.util.Log
import android.widget.EditText
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.ui.BookShelfUiState
import com.example.bookshelf.ui.BookShelfViewModel
import com.example.example.Items

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
                BookListScreen(bookShelfUiState.items.items)
            }
            is BookShelfUiState.Error -> {
                Text("Error: " + bookShelfUiState.message)
            }
        }
    }
}

@Composable
fun BookListScreen(
    items: ArrayList<Items>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),

    ) {
        items(items = items) { item ->
            BookItem(item)
        }
    }
}

@Composable
fun BookItem(
    item: Items
) {
    val title: String = item.volumeInfo?.title ?: "<No title>"
    val authors: List<String> = item.volumeInfo?.authors ?: emptyList()
    val publisher: String = item.volumeInfo?.publisher ?: "<No publisher>"
    val publishedDate: String = item.volumeInfo?.publishedDate ?: "<No published date>"
    var imageLinks: String = item.volumeInfo?.imageLinks?.smallThumbnail ?: "<No image>"
    imageLinks += ".png"

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(8.dp)
                    .size(64.dp),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imageLinks)
                    .crossfade(true)
                    .build(),
                contentDescription = title,
                contentScale = ContentScale.FillWidth,
                alignment = androidx.compose.ui.Alignment.Center
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(16.dp),
                    style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Start
                )
                Text(
                    text = authors.toString(),
                    modifier = Modifier
                        .padding(8.dp),
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Start
                )
                Text(
                    text = publisher,
                    modifier = Modifier
                        .padding(8.dp),
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Start
                )
                Text(
                    text = publishedDate,
                    modifier = Modifier
                        .padding(4.dp),
                    style = androidx.compose.material3.MaterialTheme.typography.titleSmall,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Start
                )
            }
        }
    }
}

