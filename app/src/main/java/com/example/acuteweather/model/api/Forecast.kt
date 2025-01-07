package com.example.weatherapp.model.response.forecast

import com.google.gson.annotations.SerializedName

data class Forecast (
    @SerializedName("forecastday") var forecastDay : List<ForecastDay>
)