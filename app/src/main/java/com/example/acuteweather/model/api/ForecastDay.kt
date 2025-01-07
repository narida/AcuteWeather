package com.example.weatherapp.model.response.forecast

import com.google.gson.annotations.SerializedName

data class ForecastDay (
    @SerializedName("date") var date : String,
    @SerializedName("date_epoch") var dateEpoch : Int,
    @SerializedName("day") var day : Day,
    @SerializedName("hour") var hour : List<Hour>
)