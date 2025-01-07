package com.example.weatherapp.model.response.forecast

import com.google.gson.annotations.SerializedName

data class Current (
    @SerializedName("last_updated_epoch") var lastUpdatedEpoch : Int,
    @SerializedName("last_updated") var lastUpdated : String,
    @SerializedName("temp_c") var tempC : Double,
    @SerializedName("temp_f") var tempF : Double,
    @SerializedName("is_day") var isDay : Int,
    @SerializedName("condition") var condition : Condition,
    @SerializedName("wind_mph") var windMph : Double,
    @SerializedName("wind_kph") var windKph : Double,
    @SerializedName("wind_degree") var windDegree : Double,
    @SerializedName("wind_dir") var windDir : String,
    @SerializedName("pressure_mb") var pressureMb : Double,
    @SerializedName("pressure_in") var pressureIn : Double,
    @SerializedName("precip_mm") var precipMm : Double,
    @SerializedName("precip_in") var precipIn : Double,
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
    @SerializedName("vis_km") var visKm : Double,
    @SerializedName("vis_miles") var visMiles : Double,
    @SerializedName("uv") var uv : Double,
    @SerializedName("gust_mph") var gustMph : Double,
    @SerializedName("gust_kph") var gustKph : Double
)