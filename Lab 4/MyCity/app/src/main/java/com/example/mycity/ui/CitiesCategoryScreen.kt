package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycity.R
import com.example.mycity.model.City
import com.example.mycity.ui.theme.MyCityTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesCategoryScreen(
    cities: List<City>,
    modifier: Modifier = Modifier,
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            CitiesTopAppBar(
                title = "Cities",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        CitiesBody(
            cities = cities,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun CitiesBody(
    cities: List<City>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(cities,  key = {it.id}) { city ->
            CityCard(
                city = city,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityCard(
    city: City,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        onClick = { /*TODO*/ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(128.dp)
        ) {
            Image(
                painter = painterResource(id = city.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .width(128.dp)
                    .height(128.dp)
                )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    )
            ) {
                Text(
                    text = stringResource(id = city.name),
                    fontSize = 16.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)

                )
                Text(
                    text = stringResource(id = city.description),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CitiesScreenPreview() {
    MyCityTheme {
        CitiesCategoryScreen(
            cities = listOf(
                City(id = 1,
                    name = R.string.city_name_1,
                    description = R.string.description_1,
                    imageResourceId = R.drawable.city,
                    ),
                City(id = 2,
                    name = R.string.city_name_2,
                    description = R.string.description_2,
                    imageResourceId = R.drawable.city,
                    ),
                City(id = 3,
                    name = R.string.city_name_3,
                    description = R.string.description_3,
                    imageResourceId = R.drawable.city,
                    ),
                City(id = 4,
                    name = R.string.city_name_4,
                    description = R.string.description_4,
                    imageResourceId = R.drawable.city,
                    ),
                City(id = 5,
                    name = R.string.city_name_5,
                    description = R.string.description_5,
                    imageResourceId = R.drawable.city,
                    ),

            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityBodyPreview() {
    MyCityTheme {
        CitiesBody(
            cities = listOf(
                City(id = 1,
                    name = R.string.city_name_1,
                    description = R.string.description_1,
                    imageResourceId = R.drawable.city,
                    ),
                City(id = 2,
                    name = R.string.city_name_2,
                    description = R.string.description_2,
                    imageResourceId = R.drawable.city,
                    ),
                City(id = 3,
                    name = R.string.city_name_3,
                    description = R.string.description_3,
                    imageResourceId = R.drawable.city,
                    ),
                City(id = 4,
                    name = R.string.city_name_4,
                    description = R.string.description_4,
                    imageResourceId = R.drawable.city,
                    ),
                City(id = 5,
                    name = R.string.city_name_5,
                    description = R.string.description_5,
                    imageResourceId = R.drawable.city,
                    ),
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CityItemPreview() {
    MyCityTheme {
        CityCard(
            City(id = 1,
                name = R.string.city_name_1,
                description = R.string.description_1,
                imageResourceId = R.drawable.city,
                )
        )
    }
}