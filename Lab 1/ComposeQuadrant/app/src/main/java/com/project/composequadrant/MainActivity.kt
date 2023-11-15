package com.project.composequadrant


import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainMenuApp()
                }
            }
        }
    }
}

@Composable
fun MainMenuApp() {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            ComposablePart(
                title = stringResource(id = R.string.title1),
                text = stringResource(id = R.string.text1),
                color = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            ComposablePart(
                title = stringResource(id = R.string.title2),
                text = stringResource(id = R.string.text2),
                color = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            ComposablePart(
                title = stringResource(id = R.string.title3),
                text = stringResource(id = R.string.text3),
                color = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )
            ComposablePart(
                title = stringResource(id = R.string.title4),
                text = stringResource(id = R.string.text4),
                color = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ComposablePart(
    title: String,
    text: String,
    color: Color,
    modifier: Modifier
) {
    Column(modifier = modifier
        .fillMaxSize()
        .background(color = color)
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text,
            textAlign = TextAlign.Justify
        )
    }
}
