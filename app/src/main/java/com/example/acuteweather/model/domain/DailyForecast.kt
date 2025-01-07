package com.example.acuteweather.model.domain

data class DailyForecast(
    val day: String,
    val highTemp: String,
    val lowTemp: String,
    val icon: Int
)