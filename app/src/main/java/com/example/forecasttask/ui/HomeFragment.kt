package com.example.forecasttask.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forecasttask.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import android.content.DialogInterface
import android.app.AlertDialog
import android.util.Log
import com.google.android.material.textfield.TextInputEditText

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.forecasttask.db.CityEntity
import com.example.forecasttask.helper.CitiesAdapter
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment(), View.OnClickListener {

    private var mLocationPermissionGranted: Boolean = false
    private lateinit var city: String
    private lateinit var viewModel: HomeViewModel
    private var citiesList: List<CityEntity>? = null
    private lateinit var citiesAdapter: CitiesAdapter


    companion object {
        fun newInstance() = HomeFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.example.forecasttask.R.layout.home_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    @SuppressLint("CheckResult", "NotifyDataSetChanged")
    private fun init() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.init(requireActivity())
        addBtn.setOnClickListener(this)
        citiesRV.apply {
            layoutManager =
                GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()

        }
        getCities()
        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            searchBar.setOnQueryTextListener(object : OnQueryTextListener {

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.length==0){
                        getCities()
                    }else{
                        subscriber.onNext(newText!!)
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    subscriber.onNext(query!!)
                    return true
                }
            })


        }).doOnNext { s ->
            Log.d("TAG", "testTag  search: $s")
        }.debounce(2, TimeUnit.SECONDS)
            .distinctUntilChanged()
            .subscribe { text ->
                //viewModel.search(text)
                Log.d("TAG", "testTag  final search: $text")

            }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getCities() {
        viewModel.getCities()?.observe(requireActivity(), Observer {
            citiesList=null
            citiesList = it
            Log.d("TAG", "testTag getCities: ${citiesList?.size} ")
            citiesAdapter = citiesList?.let { it1 -> CitiesAdapter(it1, requireActivity()) }!!
            citiesAdapter.notifyDataSetChanged()
            citiesRV.adapter = citiesAdapter

        })
    }

    override fun onClick(view: View?) {
        if (addBtn == view) {
            if (citiesList?.size != 5) {
                showDialog()

            } else {
                Toast.makeText(requireContext(), "5 cities is a max", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showDialog() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val viewInflated: View = LayoutInflater.from(context)
            .inflate(com.example.forecasttask.R.layout.enter_city_dialog, view as ViewGroup?, false)
        val cityTI =
            viewInflated.findViewById<View>(com.example.forecasttask.R.id.cityTI) as TextInputEditText
        builder.setView(viewInflated)
        builder.setPositiveButton(android.R.string.ok,
            DialogInterface.OnClickListener { dialog, which ->

                city = cityTI.text?.trim().toString().lowercase()
                Log.d("TAG", "testTag showDialog: $city")

                if (city.isEmpty()) {
                    cityTI.error = "please enter a city name"
                } else {
                    viewModel.insertCityToRoom(city, false)
                    getCities()

                }
            })
        builder.setNegativeButton(android.R.string.cancel,
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()


    }

}

