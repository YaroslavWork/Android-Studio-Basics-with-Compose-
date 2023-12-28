package com.example.wifimonitor.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import co.yml.charts.common.model.Point
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import kotlin.math.abs
import kotlin.math.roundToInt

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
            LineChartData(
                uiState = uiState.value
            )
            Text(
                text = stringResource(R.string.chart_legend),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_small)),
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item{
                    Text(
                        stringResource(R.string.title_recorded_active_wi_fi),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                items(uiState.value.wifiStates) { information ->
                     RecordsWifiCard(
                            ssidString = information.ssid,
                            bssidString = information.bssid.toString(),
                            estimatedDistanceString = information.estimatedDistance.toString(),
                            rssiString = information.rssi.toString(),
                            linkSpeed = information.linkSpeed.toString(),
                            frequencyString = information.frequency.toString(),
                            recordTimeString = information.recordTime,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                         )
                }
            }
        }
    }
}

@Composable
fun LineChartData(
    uiState: WifiInformationUiState
) {
    var pointsData = uiState.pointsData

    if (pointsData.isEmpty()) {
        pointsData = listOf(
            Point(0f, 0f),
            Point(1f, 1f),
            Point(2f, 2f),
        )
    }

    val xAxisData = AxisData.Builder()
        .axisStepSize(7.dp)
        .backgroundColor(Color.Transparent)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .build()

    val maxYValue = pointsData.maxByOrNull { it.y }?.y ?: 0f
    val minYValue = pointsData.minByOrNull { it.y }?.y ?: 0f

    val YAxisSteps = 3

    val yAxisData = AxisData.Builder()
        .steps(YAxisSteps)
        .axisStepSize(10.dp)
        .backgroundColor(Color.Transparent)
        .labelData { i ->
            val yScale = (abs(minYValue)-abs(maxYValue)) / YAxisSteps
            //val scaledValue = String.format("%.1f", minYValue + (i * yScale)).toDouble()
            val scaledValue =(minYValue + (i * yScale)).toDouble()
            scaledValue.toString()
        }
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = MaterialTheme.colorScheme.tertiary
                    ),
                    IntersectionPoint(
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        radius = 3.dp
                    ),
                )
            )
        ),
        backgroundColor = MaterialTheme.colorScheme.background,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = MaterialTheme.colorScheme.tertiaryContainer),
    )

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        lineChartData = lineChartData
    )
}

@Composable
fun RecordsWifiCard(
    ssidString: String,
    bssidString: String,
    estimatedDistanceString: String,
    rssiString: String,
    linkSpeed: String,
    frequencyString: String,
    recordTimeString: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = ssidString,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = recordTimeString,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            Column() {
                InformationRow(
                    title = stringResource(R.string.title_ssid),
                    information = ssidString,
                )
                InformationRow(
                    title = stringResource(R.string.title_bssid),
                    information = bssidString
                )
                InformationRow(
                    title = stringResource(R.string.title_estimated_distance),
                    information = "$estimatedDistanceString m"
                )
                InformationRow(
                    title = stringResource(R.string.title_link_speed),
                    information = "$linkSpeed Mbps"
                )
                InformationRow(
                    title = stringResource(R.string.title_rssi),
                    information = "$rssiString dBm"
                )
                InformationRow(
                    title = stringResource(R.string.title_frequency),
                    information = "$frequencyString MHz"
                )
            }
        }
    }
}

