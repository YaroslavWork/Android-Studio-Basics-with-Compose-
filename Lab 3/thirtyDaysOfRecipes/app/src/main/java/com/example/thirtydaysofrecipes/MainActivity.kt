package com.example.thirtydaysofrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirtydaysofrecipes.data.Recipy
import com.example.thirtydaysofrecipes.data.recipes
import com.example.thirtydaysofrecipes.ui.theme.ThirtyDaysOfRecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirtyDaysOfRecipesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipyApp() {
    Scaffold(
        topBar = {
            RecipyTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(recipes) {
                RecipyItem(
                    recipy = it,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipyTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(

            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun RecipyItem(
    recipy: Recipy,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
        label = "",
    )

    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color)
        ) {
            RecipyTitle(
                expanded = expanded,
                onClick = { expanded = !expanded },
                text_id = recipy.id,
                text_name = recipy.name
            )
            Image(
                modifier = modifier
                    .size(dimensionResource(id = R.dimen.image_size))
                    .padding(dimensionResource(id = R.dimen.padding_small)),
                painter = painterResource(recipy.imageResourceId),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center,
                contentDescription = null,
            )
            if (expanded) {
                Text(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
                    text = stringResource(id = recipy.description)
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            }
        }
    }

}

@Composable
fun RecipyTitle(
    expanded: Boolean,
    onClick: () -> Unit,
    text_id: Int,
    @StringRes text_name: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        Column {
            Text(text = "Day $text_id", style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            Text(text = stringResource(id = text_name), style = MaterialTheme.typography.displayMedium)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onClick,
            modifier = modifier
        ) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = stringResource(id = R.string.expand_button_content_description),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ThirtyDaysOfRecipesTheme {
        RecipyApp()
    }
}