package com.example.acuteweather

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.acuteweather.model.domain.CurrentForecast
import com.example.acuteweather.model.domain.DailyForecast
import com.example.acuteweather.model.domain.HourlyForecast
import com.example.acuteweather.ui.theme.AcuteWeatherTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_LONG)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainViewModel(application)

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            setupLayout()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun setupLayout() {
        enableEdgeToEdge()
        setContent {
            AcuteWeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun WeatherScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val weather = viewModel.currentForecast.observeAsState().value
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchWeather()
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Search Bar
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text(stringResource(R.string.search_location)) },
            placeholder = { Text(stringResource(R.string.enter_city_or_place)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (searchQuery.isNotEmpty()) {
                        // TODO: Add search function here
                        // viewModel.fetchWeather(searchQuery.trim())
                    }
                }
            ),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") }
        )

        weather?.let {
            Column(modifier = modifier.padding(16.dp)) {
                CurrentWeatherCard(weatherData = it.current)

                Text(
                    text = stringResource(R.string.hourly),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 16.dp)
                )
                LazyRow {
                    items(it.hourly) { hourly ->
                        HourlyForecastCard(hourly)
                    }
                }

                Text(
                    text = stringResource(R.string.future_forecast),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 16.dp)
                )
                LazyColumn {
                    items(it.daily) { daily ->
                        DailyForecastCard(daily)
                    }
                }
            }
        }  ?: run {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun CurrentWeatherCard(weatherData: CurrentForecast) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(weatherData.city, style = MaterialTheme.typography.titleLarge)
        Text(weatherData.temperature, style = MaterialTheme.typography.titleMedium)
        Text(weatherData.condition, style = MaterialTheme.typography.titleSmall)
        AsyncImage(
            model = weatherData.icon,
            contentDescription = "Icon",
            modifier = Modifier.size(64.dp)
        )
    }
}

@Composable
fun HourlyForecastCard(hourly: HourlyForecast) {
    Card(modifier = Modifier
        .padding(8.dp)
        .size(100.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painterResource(id = hourly.icon),
                contentDescription = "Weather Icon",
                modifier = Modifier
                    .size(48.dp)
                    .padding(top = 8.dp)
            )
            Text(hourly.time,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Center)
            Text(hourly.temperature, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun DailyForecastCard(daily: DailyForecast) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(id = daily.icon),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(48.dp)
            )
            Column(modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)) {
                Text(daily.day, style = MaterialTheme.typography.labelLarge)
                Text(
                    "High: ${daily.highTemp} / Low: ${daily.lowTemp}",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}