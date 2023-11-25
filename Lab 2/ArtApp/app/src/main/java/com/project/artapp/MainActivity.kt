package com.project.artapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.artapp.ui.theme.ArtAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtAppLayout()
                }
            }
        }
    }
}

@Composable
fun ArtAppLayout() {
    var currentPaint by remember { mutableStateOf(0) }
    val imageResource = when(currentPaint) {
        0 -> R.drawable.paint_1
        1 -> R.drawable.paint_2
        else -> R.drawable.paint_3
    }
    val artworkTitle = when(currentPaint) {
        0 -> stringResource(R.string.title_1)
        1 -> stringResource(R.string.title_2)
        else -> stringResource(R.string.title_3)
    }
    val artworkDescription = when(currentPaint) {
        0 -> stringResource(R.string.description_1)
        1 -> stringResource(R.string.description_2)
        else -> stringResource(R.string.description_3)
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        ArtworkWall(paintId = imageResource, modifier = Modifier)
        Spacer(modifier = Modifier.height(10.dp))
        ArtworkDescriptor(
            artworkTitle = artworkTitle,
            artworkDescription = artworkDescription,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(40.dp))
        DisplayController(
            previousOnClick = {
                if (currentPaint > 0)
                    currentPaint -= 1
                              },
            nextOnClick = {
                if (currentPaint <= 2)
                    currentPaint += 1
                          },
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(140.dp))
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes paintId: Int,
    modifier: Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = paintId),
            contentDescription = "1"
        )
    }

}

@Composable
fun ArtworkDescriptor(artworkTitle: String, artworkDescription: String, modifier: Modifier) {
    Column (
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = artworkTitle,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text (
            text = artworkDescription,
        )
    }
}

@Composable
fun DisplayController(previousOnClick: () -> Unit, nextOnClick: () -> Unit, modifier: Modifier) {
    Row (
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    )
    {
        Button(onClick = previousOnClick) {
            Text(text = "Previous")
        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(onClick = nextOnClick) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtAppTheme {
        ArtAppLayout()
    }
}