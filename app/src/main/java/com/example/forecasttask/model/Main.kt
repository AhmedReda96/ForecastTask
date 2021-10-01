package com.example.forecastapp.model

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName


data class Main(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feelsLike")
    val feelsLike: Double,
    @SerializedName("tempMin")
    val tempMin: Double,
    @SerializedName("tempMax")
    val tempMax: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("seaLevel")
    val seaLevel: Int,
    @SerializedName("grndLevel")
    val grndLevel: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("tempKf")
    val tempKf: Double
)