package com.example.acuteweather.model.api

import com.example.weatherapp.model.response.forecast.Current
import com.example.weatherapp.model.response.forecast.Forecast
import com.example.weatherapp.model.response.forecast.Location
import com.google.gson.annotations.SerializedName

data class ForecastedWeatherResponse (
    @SerializedName("location") var location : Location? = Location(),
    @SerializedName("current") var current : Current,
    @SerializedName("forecast") var forecast : Forecast
)