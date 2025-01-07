package com.example.acuteweather.model.domain

data class HourlyForecast(
    val time: String,
    val temperature: String,
    val icon: Int
)
