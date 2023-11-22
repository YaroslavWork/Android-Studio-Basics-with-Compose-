package com.project.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.project.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme() {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var curStep by remember { mutableStateOf(1) }
    var lemonCount by remember { mutableStateOf(0) }

    Scaffold { innerPadding ->
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colors.background),
            color = MaterialTheme.colors.background
        ) {
            when (curStep) {
                1 -> {
                    LemonTextAndImage(
                        textLabelId = R.string.header1,
                        drawableResourceId = R.drawable.lemon_tree,
                        descriptionId = R.string.text1,
                        onImageClick = {
                            curStep = 2
                            lemonCount = (2..4).random()
                        })
                }
                2 -> {
                    LemonTextAndImage(
                        textLabelId = R.string.header2,
                        drawableResourceId = R.drawable.lemon_squeeze,
                        descriptionId = R.string.text2,
                        onImageClick = {
                            lemonCount--
                            if (lemonCount == 0) {
                                curStep = 3
                            }
                        })
                }
                3 -> {
                    LemonTextAndImage(
                        textLabelId = R.string.header3,
                        drawableResourceId = R.drawable.lemon_drink,
                        descriptionId = R.string.text3,
                        onImageClick = {
                            curStep = 4
                        })
                }
                4 -> {
                    LemonTextAndImage(
                        textLabelId = R.string.header4,
                        drawableResourceId = R.drawable.lemon_restart,
                        descriptionId = R.string.text4,
                        onImageClick = {
                            curStep = 1
                        })
                }
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelId: Int,
    drawableResourceId: Int,
    descriptionId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier,
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(id = descriptionId),
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.image_width))
                        .height(dimensionResource(id = R.dimen.image_height))
                        .padding(dimensionResource(id = R.dimen.button_interior_padding))
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_between_text_and_image)))
            Text (
                text = stringResource(id = descriptionId),

            )
        }
    }
}