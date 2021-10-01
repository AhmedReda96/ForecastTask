package com.example.forecastapp.model

import com.google.gson.annotations.SerializedName

data class NewsList (

    @SerializedName("dt")
        val dt: Int,
    @SerializedName("weather")
        val weather: List<Weather>,
    @SerializedName("clouds")
        val clouds: Clouds,
    @SerializedName("wind")
        val wind: Wind,
    @SerializedName("visibility")
        val visibility: Int,
    @SerializedName("pop")
        val pop: Double,
    @SerializedName("sys")
        val sys: Sys,
    @SerializedName("rain")
        val rain: Rain,
    @SerializedName("dt_txt")
        val dtTxt: String
    )
