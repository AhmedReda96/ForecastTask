package com.example.forecasttask.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
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
import kotlinx.android.synthetic.main.city_item_model.view.*
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.lifecycle.ViewModelProviders


class CitiesAdapter() : Adapter<ViewHolder>(), Parcelable {
    private var citiesList: List<CityEntity> = ArrayList()
    private lateinit var context: Context
    private lateinit var viewModel: HomeViewModel

    constructor(parcel: Parcel) : this() {

    }


    constructor(
        citiesList: List<CityEntity>,
        context: Context,
    ) : this() {
        this.citiesList = citiesList
        this.context = context
        viewModel =
            ViewModelProviders.of((context as FragmentActivity)!!).get(HomeViewModel::class.java)
        viewModel.init(context)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item_model, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
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
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CitiesAdapter> {
        override fun createFromParcel(parcel: Parcel): CitiesAdapter {
            return CitiesAdapter(parcel)
        }

        override fun newArray(size: Int): Array<CitiesAdapter?> {
            return arrayOfNulls(size)
        }
    }


}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val deleteBtn: ImageButton = itemView.deleteBtn
    val cityName: TextView = itemView.cityName


}

