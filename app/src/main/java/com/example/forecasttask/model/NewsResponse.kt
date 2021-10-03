package com.example.forecasttask.model

import com.google.gson.annotations.SerializedName


public data class NewsResponse(
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("message")
    val message: Int?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("list")
    val news: List<NewsList>,
)