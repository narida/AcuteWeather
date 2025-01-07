package com.example.acuteweather.util

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.acuteweather.model.domain.LocationEntity

@Database(entities = [LocationEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}