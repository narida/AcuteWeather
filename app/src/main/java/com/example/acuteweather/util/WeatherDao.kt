package com.example.acuteweather.util

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.acuteweather.model.domain.LocationEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity)

    @Query("SELECT * FROM LocationEntity")
    fun getAllLocations(): LiveData<List<LocationEntity>>

    @Delete
    suspend fun deleteLocation(location: LocationEntity)
}