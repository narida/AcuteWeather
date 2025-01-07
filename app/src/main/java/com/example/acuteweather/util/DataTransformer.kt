package com.example.acuteweather.util

import android.annotation.SuppressLint
import com.example.acuteweather.R
import com.example.acuteweather.model.api.ForecastedWeatherResponse
import com.example.acuteweather.model.domain.CurrentForecast
import com.example.acuteweather.model.domain.DailyForecast
import com.example.acuteweather.model.domain.HourlyForecast
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DataTransformer {
    fun transformToCurrentForecast(response: ForecastedWeatherResponse): CurrentForecast {
        return CurrentForecast(
            city = response.location?.name.toString(),
            temperature = response.current.tempC.toString(),
            condition = response.current.condition.text.toString(),
            icon = R.drawable.ic_launcher_background)
    }

    fun transformToDailyForecast(response: ForecastedWeatherResponse): List<DailyForecast> {
        return response.forecast.forecastDay.map {
            DailyForecast(
                day = convertEpochToDate(it.dateEpoch.toLong()),
                highTemp = it.day.maxtempC.toString(),
                lowTemp = it.day.mintempC.toString(),
                icon = R.drawable.ic_launcher_background
            )
        }
    }

    fun transformToHourlyForecasts(response: ForecastedWeatherResponse): List<HourlyForecast> {
        val forecastDays = response.forecast.forecastDay.flatMap { it.hour }
        return forecastDays.map {
            HourlyForecast(
                time = convertEpochToTime(it.timeEpoch.toLong()),
                temperature = it.tempC.toString(),
                icon = R.drawable.ic_launcher_background
            )
        }
    }

    @SuppressLint("NewApi")
    private fun convertEpochToDate(date: Long): String {
        return Instant.ofEpochSecond(date)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern("MMMM d")).toString()
    }

    @SuppressLint("NewApi")
    private fun convertEpochToTime(time: Long): String {
        return Instant.ofEpochSecond(time)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern("h:00 a")).toString()
    }
}