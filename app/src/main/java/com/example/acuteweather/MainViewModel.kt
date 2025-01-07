package com.example.acuteweather

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.acuteweather.model.domain.ForecastData
import com.example.acuteweather.util.ApiClient
import com.example.acuteweather.util.DataTransformer
import com.example.acuteweather.util.WeatherDao
import com.example.acuteweather.util.WeatherDatabase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val weatherDao: WeatherDao
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    private var _currentForecast = MutableLiveData<ForecastData>()

    val currentForecast: LiveData<ForecastData> get() = _currentForecast

    init {
        val db = Room.databaseBuilder(application, WeatherDatabase::class.java, "db_weather").build()
        weatherDao = db.weatherDao()
    }

    fun fetchWeather() {
        viewModelScope.launch {
            fusedLocationClient.lastLocation.addOnSuccessListener {
                ApiClient.getForecastedWeather(
                    it.latitude,
                    it.longitude,
                    onSuccess = { data ->
                        val current = DataTransformer.transformToCurrentForecast(data)
                        val daily = DataTransformer.transformToDailyForecast(data)
                        val hourly = DataTransformer.transformToHourlyForecasts(data)
                        val forecastedData = ForecastData(
                            current = current,
                            daily =  daily,
                            hourly = hourly
                        )
                        _currentForecast.postValue(forecastedData)
                    },
                    onFailure = { error ->
                        println("Error: $error")
                    })
            }
        }
    }
}