package com.example.mycity.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
    modifier: Modifier = Modifier,
    onCancelClick : () -> Unit = {}
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            CitiesTopAppBar(
                title = "Cities: Region",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            LocalCard(
                text = "Center",
                modifier = Modifier
                    .padding(16.dp)
            )
            LocalCard(
                text = "North",
                modifier = Modifier
                    .padding(16.dp)
            )
            LocalCard(
                text = "South",
                modifier = Modifier
                    .padding(16.dp)
            )
            LocalCard(
                text = "East",
                modifier = Modifier
                    .padding(16.dp)
            )
            LocalCard(
                text = "West",
                modifier = Modifier
                    .padding(16.dp)
            )
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalCard(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = { /*TODO*/ }

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
        CitiesLocalCategoryScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun CityLocalItemPreview() {
    MyCityTheme {
        LocalCard(text = "Center")
    }
}