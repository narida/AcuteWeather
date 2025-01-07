package com.example.weatherapp.model.response.forecast

import com.google.gson.annotations.SerializedName

data class Day (
    @SerializedName("maxtemp_c") var maxtempC : Double,
    @SerializedName("maxtemp_f") var maxtempF : Double,
    @SerializedName("mintemp_c") var mintempC : Double,
    @SerializedName("mintemp_f") var mintempF : Double,
    @SerializedName("avgtemp_c") var avgtempC : Double,
    @SerializedName("avgtemp_f") var avgtempF : Double,
    @SerializedName("maxwind_mph") var maxwindMph : Double,
    @SerializedName("maxwind_kph") var maxwindKph : Double,
    @SerializedName("totalprecip_mm") var totalprecipMm : Double,
    @SerializedName("totalprecip_in") var totalprecipIn : Double,
    @SerializedName("totalsnow_cm") var totalsnowCm : Double,
    @SerializedName("avgvis_km") var avgvisKm : Double,
    @SerializedName("avgvis_miles") var avgvisMiles : Double,
    @SerializedName("avghumidity") var avghumidity : Double,
    @SerializedName("daily_will_it_rain") var dailyWillItRain : Double,
    @SerializedName("daily_chance_of_rain") var dailyChanceOfRain : Double,
    @SerializedName("daily_will_it_snow") var dailyWillItSnow : Double,
    @SerializedName("daily_chance_of_snow") var dailyChanceOfSnow : Double,
    @SerializedName("condition") var condition : Condition,
    @SerializedName("uv") var uv : Double
)