package com.example.acuteweather.util

import android.util.Log
import com.example.acuteweather.model.api.ForecastedWeatherResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object ApiClient {
    private const val BASE_URL = "http://api.weatherapi.com/v1"
    private const val API_KEY = "c0f8d5e635d24736afc90615242611" //TODO: Move this into more secured storage

    private val client = OkHttpClient()

    fun getForecastedWeather(lat: Double, lon: Double, onSuccess: (ForecastedWeatherResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url: HttpUrl = "$BASE_URL/forecast.json?".toHttpUrlOrNull()!!.newBuilder()
                    .addQueryParameter("key", API_KEY)  // Your API key
                    .addQueryParameter("q", "$lat,$lon")  // Latitude and Longitude as query parameter
                    .addQueryParameter("days", "5")
                    .build()

                Log.d("URL: ", url.toString())
                val request =
                    Request.Builder().url(url)
                        .build()
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    // Call onSuccess on the main thread to update the UI
                        val data = Gson().fromJson(
                            response.body?.string(),
                            ForecastedWeatherResponse::class.java)
                        Log.d("Data: ", data.toString())
                        onSuccess(data)
                } else {
                    // Handle failure scenario
                        onFailure(Exception("Request failed with code ${response.code}"))
                        Log.d("ERROR: ", response.body.toString())
                }
            } catch (e: Exception) {
                Log.d("ERROR: ", e.toString())
                onFailure(e)
            }
        }
    }
}
