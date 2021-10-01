package com.example.forecasttask.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forecasttask.db.CityEntity
import com.example.forecasttask.repo.HomeRepository

class HomeViewModel() : ViewModel() {
    private lateinit var repo: HomeRepository
    var citiesMutableLiveData: MutableLiveData<List<CityEntity>> = MutableLiveData<List<CityEntity>>()

    fun init(context: Context) {
        repo = HomeRepository(context)

    }

    fun getCities(): LiveData<List<CityEntity>>? {
        return repo.getCitiesFromRoom()
    }

    fun insertCityToRoom(cityName: String, default: Boolean): Boolean {
        return repo.insertCityToRoom(CityEntity(cityName, default))
    }


    fun deleteCity(id:Int) {
        return repo.deleteCity(id)
    }

    fun search(query:String): LiveData<List<CityEntity>>? {

        return repo?.search(query)




    }
}
