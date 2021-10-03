package com.example.forecasttask.helper

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forecasttask.R
import com.example.forecasttask.model.NewsList
import kotlinx.android.synthetic.main.news_item_model.view.*

class NewsAdapter() : RecyclerView.Adapter<ViewHolder>(), Parcelable {
    private var newsList: List<NewsList> = ArrayList()
    private lateinit var context: Context

    constructor(parcel: Parcel) : this() {

    }


    constructor(
        newsList: List<NewsList>,
        context: Context,
    ) : this() {
        this.newsList = newsList
        this.context = context

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item_model, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: NewsList = newsList[position]


        holder.date.text = model.dtTxt
        holder.description.text = model.weather[0].description



    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsAdapter> {
        override fun createFromParcel(parcel: Parcel): NewsAdapter {
            return NewsAdapter(parcel)
        }

        override fun newArray(size: Int): Array<NewsAdapter?> {
            return arrayOfNulls(size)
        }
    }


}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val date: TextView = itemView.date
    val description: TextView = itemView.description



}

fun writeToParcel(parcel: Parcel, flags: Int) {

}

fun describeContents(): Int {
    return 0
}

object CREATOR : Parcelable.Creator<NewsAdapter> {
    override fun createFromParcel(parcel: Parcel): NewsAdapter {
        return NewsAdapter(parcel)
    }

    override fun newArray(size: Int): Array<NewsAdapter?> {
        return arrayOfNulls(size)
    }
}

