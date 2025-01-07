package com.example.acuteweather.model.domain

data class ForecastData(
    val current: CurrentForecast,
    val daily: List<DailyForecast>,
    val hourly: List<HourlyForecast>
)