package com.example.forecasttask.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.forecasttask.db.CityDao
import com.example.forecasttask.db.CityEntity
import com.example.forecasttask.db.MainDataBase
import io.reactivex.Completable
import io.reactivex.Observable
import kotlinx.coroutines.*

class HomeRepository(private val context: Context) {
    private val TAG = "Repository"
    private var dao: CityDao? = null
    private val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )

    init {
        dao = MainDataBase.getInstance(context)?.cityDao()
    }

    fun getCitiesFromRoom(): LiveData<List<CityEntity>>? {
        return dao?.getCities()
    }

    fun insertCityToRoom(cityEntity: CityEntity): Boolean {
        var isInsert = false
        scope.launch {
            dao!!.insertCity(cityEntity).let {
                isInsert = true
                Log.d(TAG, "testTag Repo:insertNewsToRoom isSuccessful ")
            }
        }
        return isInsert
    }


    fun deleteCity(id: Int) {

        scope.launch {
            dao?.delete(id).let {
                Log.d(TAG, "testTag Repo:delete city where id =$id isSuccessful ")
            }

        }
    }

    fun search(query:String): LiveData<List<CityEntity>>? {

        return dao?.searchCity(query)





    }

}