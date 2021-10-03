package com.example.forecasttask.db
import androidx.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.Completable
import androidx.room.*
import io.reactivex.Flowable
import java.util.*

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCity(cityEntity: CityEntity)

    @Query("SELECT *  From citiesTable ")
    fun getCities(): LiveData<List<CityEntity>>?

    @Query("SELECT EXISTS(SELECT * FROM citiesTable WHERE name = :name)")
    suspend  fun isCityIsExist(name : String) : Boolean

    @Query("DELETE FROM citiesTable WHERE id = :id")
    suspend fun delete(id:Int)

    @Query("SELECT * FROM citiesTable WHERE name LIKE  :search_query ")
    fun searchCity(search_query: String?):List<CityEntity>
}