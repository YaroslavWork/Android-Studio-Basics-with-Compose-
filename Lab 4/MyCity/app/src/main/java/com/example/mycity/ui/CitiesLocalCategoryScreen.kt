package com.example.mycity.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycity.R
import com.example.mycity.model.City
import com.example.mycity.ui.theme.MyCityTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesLocalCategoryScreen(
    regions: List<String>,
    modifier: Modifier = Modifier,
    onCancelClick : () -> Unit = {},
    onNextClick : (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
    ) {
        LazyColumn(modifier = modifier) {
            items(regions) {
                LocalCard(
                    text = it,
                    modifier = Modifier
                        .padding(16.dp),
                    onNextClick = { onNextClick(it) }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onCancelClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(16.dp)
        ) {
            Text(
                text = "Cancel",
                fontSize = 16.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalCard(
    text: String,
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit = {},
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = onNextClick

    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityLocalPreview() {
    MyCityTheme {
        CitiesLocalCategoryScreen(listOf("Center", "North", "South", "East", "West"))
    }
}

@Preview(showBackground = true)
@Composable
fun CityLocalItemPreview() {
    MyCityTheme {
        LocalCard(text = "Center")
    }
}