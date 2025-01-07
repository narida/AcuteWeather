package com.example.acuteweather.model.domain

data class CurrentForecast(
    val city: String,
    val temperature: String,
    val condition: String,
    val icon: Int // Resource ID for weather icon
)