package com.example.forecasttask.api

import com.example.forecasttask.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") q: String?,
        @Query("appid") appid: String?,
    ): Response<NewsResponse?>?


}