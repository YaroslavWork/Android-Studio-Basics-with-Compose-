package com.example.mycity.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mycity.R
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.data.LocalCitiesDataProvider

enum class CitiesScreen(@StringRes val title: Int) {
    City(title = R.string.app_name),
    Region(title = R.string.choose_region),
    Summary(title = R.string.choose_summary),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesTopAppBar(
    currentScreen: CitiesScreen,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesApp(
    viewModel: CitiesViewModel = viewModel(),
    navController : NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CitiesScreen.valueOf(
        backStackEntry?.destination?.route ?: CitiesScreen.City.name
    )

    Scaffold(
        topBar = {
            CitiesTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = CitiesScreen.City.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = CitiesScreen.City.name) {
                CitiesCategoryScreen(
                    cities = LocalCitiesDataProvider.getCitiesData(),
                    onNextClick = {
                        viewModel.updateCurrentCity(it)
                        navController.navigate(CitiesScreen.Region.name)
                        },
                )
            }
            composable(route = CitiesScreen.Region.name) {
                CitiesLocalCategoryScreen(
                    regions = LocalCitiesDataProvider.getRegions(),
                    onCancelClick = {
                        cancelOrderAndNavigateToStart(
                            viewModel = viewModel,
                            navController = navController
                        )
                    },
                    onNextClick = {
                        viewModel.updateRegion(it)
                        navController.navigate(CitiesScreen.Summary.name)
                    },
                )
            }
            composable(route = CitiesScreen.Summary.name) {
                DetailsCategoryScreen(
                    uiState = uiState,
                    onCancelClick = {
                        cancelOrderAndNavigateToStart(
                            viewModel = viewModel,
                            navController = navController
                        )
                    },
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: CitiesViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(CitiesScreen.City.name, inclusive = false)
}
