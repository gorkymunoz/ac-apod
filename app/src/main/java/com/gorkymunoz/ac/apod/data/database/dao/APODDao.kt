package com.gorkymunoz.ac.apod.data.database.dao

import androidx.room.*
import com.gorkymunoz.ac.apod.data.database.entities.APOD


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
@Dao
interface APODDao {

    @Query("SELECT * FROM APOD")
    fun getAll(): List<APOD>

    @Query("SELECT * FROM APOD WHERE date = :date")
    fun findByDate(date: String): APOD?

    @Query("SELECT COUNT(date) FROM APOD")
    fun countAPODs(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAPODs(apods: List<APOD>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAPOD(apod: APOD)

    @Update
    fun updateAPOD(apod: APOD)
}