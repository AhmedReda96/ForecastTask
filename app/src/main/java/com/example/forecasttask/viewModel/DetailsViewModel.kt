package com.example.forecasttask.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forecasttask.model.NewsList
import com.example.forecasttask.repo.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: DetailsRepository) :
    ViewModel() {

     var list = MutableLiveData<List<NewsList>>()


    fun getNews(query:String){
        repository.getNewsData(query).also {
            list = it
        }

    }

}