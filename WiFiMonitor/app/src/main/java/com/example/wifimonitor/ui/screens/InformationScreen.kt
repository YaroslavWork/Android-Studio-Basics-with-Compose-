package com.example.wifimonitor.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import co.yml.charts.common.model.Point
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wifimonitor.R
import com.example.wifimonitor.WifiMonitorTopAppBar
import com.example.wifimonitor.ui.AppViewModelProvider
import com.example.wifimonitor.ui.navigation.NavigationDestination
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp

object WifiInformationDestination : NavigationDestination {
    override val route: String = "information"
    override val titleRes: Int = R.string.wifi_information
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WifiInformationScreen(
    navigateBack: () -> Unit,
) {
    val viewModel: InformationViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    val steps = 10
    val pointsData = listOf(
        Point(0f, 0f),
        Point(1f, 190f),
        Point(2f, 0f),
        Point(3f, 60f),
        Point(4f, 10f),
    )

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Transparent)
        .labelData { index ->
            val point = pointsData[index]
            point.x.toString()
        }
        .build()

    val yAxisData = AxisData.Builder()
        .axisStepSize(10f.dp)
        .backgroundColor(Color.Transparent)
        .labelData { index ->
            val point = pointsData[index]
            point.y.toString()
        }
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = Color.Blue
                    )
                )
            )
        ),
        backgroundColor = Color.Transparent,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = Color.LightGray),
    )

    Scaffold(
        topBar = {
            WifiMonitorTopAppBar(
                title = stringResource(id = WifiInformationDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                lineChartData = lineChartData
            )
            
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(uiState.value.wifiStates) { information ->
                    Text(text = "Recorded at: ${information.recordTime}\n")
                    Text(text = "SSID: ${information.ssid}")
                    Text(text = "BSSID: ${information.bssid}")
                    Text(text = "Frequency: ${information.frequency}")
                    Text(text = "Link Speed: ${information.linkSpeed}")
                    Text(text = "RSSI: ${information.rssi}")
                    Text(text = "Estimated Distance: ${information.estimatedDistance}\n\n")
                }
            }
        }

        
    }
}