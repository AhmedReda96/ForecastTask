package com.example.forecasttask.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.forecasttask.api.Service
import com.example.forecasttask.model.NewsResponse
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject
import com.example.forecasttask.model.NewsList as NewsList1

class DetailsRepository @Inject constructor(private val service: Service) {
    val appid: String = "b4c2f09608a1a4454d14f4c677c465a7"

    private val TAG = "DetailsRepository"
    val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )

    fun getNewsData(query: String): MutableLiveData<List<com.example.forecasttask.model.NewsList>> {

        var list = MutableLiveData<List<com.example.forecasttask.model.NewsList>>()

        scope.launch {
            val response = service.getForecast(query, appid)!!

            if (response?.isSuccessful!!) {

                Log.d(
                    TAG,
                    "testTag getNews: response isSuccessful : ${response.body()?.news?.size}"
                )
                list.value = response.body()?.news

            } else {
                Log.d(
                    TAG,
                    "testTag getNews: response isNotSuccessful ${
                        response.message().toString()
                    }"
                )
            }

        }

        return list
    }


}