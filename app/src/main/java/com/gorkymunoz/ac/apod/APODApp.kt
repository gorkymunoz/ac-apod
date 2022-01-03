package com.gorkymunoz.ac.apod

import android.app.Application
import androidx.room.Room
import com.gorkymunoz.ac.apod.data.database.APODDatabase


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
class APODApp : Application() {

    lateinit var db: APODDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, APODDatabase::class.java, "apod.db").build()
    }

}