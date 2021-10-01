package com.example.forecasttask.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "citiesTable")
data class CityEntity(
    @ColumnInfo(name = "name")
    var name: String?=null,
    @ColumnInfo(name = "default")
    var default: Boolean?=null

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

}