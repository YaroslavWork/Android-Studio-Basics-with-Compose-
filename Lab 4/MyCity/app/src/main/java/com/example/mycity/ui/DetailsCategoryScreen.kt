package com.example.mycity.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycity.data.CityUiState
import com.example.mycity.ui.theme.MyCityTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsCategoryScreen(
    modifier: Modifier = Modifier,
    uiState: CitiesUiState,
    onCancelClick : () -> Unit = {}
) {
    Column(
        modifier = Modifier
    ) {
        TextRow(
            textTitle = "City: ",
            textContent = stringResource(uiState.currentCity.name),
            modifier = Modifier
                .padding(16.dp)
        )
        TextRow(
            textTitle = "Region: ",
            textContent = uiState.region,
            modifier = Modifier
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                )
        ) {
            Button(
                onClick = onCancelClick,
            ) {
                Text(
                    text = "Cancel",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { /*TODO*/ },
            ) {
                Text(
                    text = "Save",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun TextRow(
    textTitle: String,
    textContent: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(25.dp)
    ) {
        Text(
            text = textTitle,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxHeight(),
            textAlign = TextAlign.Center
        )
        Text(
            text = textContent,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxHeight(),
            textAlign = TextAlign.Center
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DetailPreview() {
//    MyCityTheme {
//        DetailsCategoryScreen()
//    }
//}

@Preview(showBackground = true)
@Composable
fun DetailItemPreview() {
    MyCityTheme {
        TextRow(textTitle = "Cities: ", textContent = "San Francisco")
    }
}