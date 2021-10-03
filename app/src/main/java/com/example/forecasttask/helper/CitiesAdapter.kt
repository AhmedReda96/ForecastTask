package com.example.forecasttask.helper

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forecasttask.R
import com.example.forecasttask.db.CityEntity
import com.example.forecasttask.viewModel.HomeViewModel
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import kotlinx.android.synthetic.main.city_item_model.view.*


class CitiesAdapter() : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {
    private var citiesList: List<CityEntity> = ArrayList()
    private lateinit var context: Context
    private lateinit var viewModel: HomeViewModel
    private lateinit var bundle: Bundle



    constructor(
        citiesList: List<CityEntity>,
        context: Context,
    ) : this() {
        this.citiesList = citiesList
        this.context = context
        viewModel =
            ViewModelProvider((context as FragmentActivity)!!)[HomeViewModel::class.java]
        viewModel.init(context)
        bundle = Bundle()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item_model, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }





    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val deleteBtn: ImageButton = itemView.deleteBtn
        val cityName: TextView = itemView.cityName


    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: CityEntity = citiesList[position]


        holder.cityName.text = model.name

        if (position != 0) {
            holder.deleteBtn.visibility = View.VISIBLE

        } else {
            holder.deleteBtn.visibility = View.INVISIBLE

        }

        holder.deleteBtn.setOnClickListener {
            model.id?.let { it1 -> viewModel.deleteCity(it1) }
        }
        holder.itemView.setOnClickListener {
            bundle.putString("cityName", model.name)
            it.findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)

        }    }


}


