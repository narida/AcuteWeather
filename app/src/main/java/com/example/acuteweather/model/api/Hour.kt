package com.example.weatherapp.model.response.forecast

import com.google.gson.annotations.SerializedName

data class Hour (
    @SerializedName("time_epoch") var timeEpoch : Int,
    @SerializedName("time") var time : String,
    @SerializedName("temp_c") var tempC : Double,
    @SerializedName("temp_f") var tempF : Double,
    @SerializedName("is_day") var isDay : Double,
    @SerializedName("condition") var condition : Condition,
    @SerializedName("wind_mph") var windMph : Double,
    @SerializedName("wind_kph") var windKph : Double,
    @SerializedName("wind_degree") var windDegree : Double,
    @SerializedName("wind_dir") var windDir : String,
    @SerializedName("pressure_mb") var pressureMb : Double,
    @SerializedName("pressure_in") var pressureIn : Double,
    @SerializedName("precip_mm") var precipMm : Double,
    @SerializedName("precip_in") var precipIn : Double,
    @SerializedName("snow_cm") var snowCm : Double,
    @SerializedName("humidity") var humidity : Double,
    @SerializedName("cloud") var cloud : Double,
    @SerializedName("feelslike_c") var feelslikeC : Double,
    @SerializedName("feelslike_f") var feelslikeF : Double,
    @SerializedName("windchill_c") var windchillC : Double,
    @SerializedName("windchill_f") var windchillF : Double,
    @SerializedName("heatindex_c") var heatindexC : Double,
    @SerializedName("heatindex_f") var heatindexF : Double,
    @SerializedName("dewpoint_c") var dewpointC : Double,
    @SerializedName("dewpoint_f") var dewpointF : Double,
    @SerializedName("will_it_rain") var willItRain : Double,
    @SerializedName("chance_of_rain") var chanceOfRain : Double,
    @SerializedName("will_it_snow") var willItSnow : Double,
    @SerializedName("chance_of_snow") var chanceOfSnow : Double,
    @SerializedName("vis_km") var visKm : Double,
    @SerializedName("vis_miles") var visMiles : Double,
    @SerializedName("gust_mph") var gustMph : Double,
    @SerializedName("gust_kph") var gustKph : Double,
    @SerializedName("uv") var uv : Double

)