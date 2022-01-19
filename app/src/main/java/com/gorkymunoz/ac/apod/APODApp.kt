package com.gorkymunoz.ac.apod

import android.app.Application
import androidx.room.Room
import com.gorkymunoz.ac.apod.data.database.APODDatabase
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
@HiltAndroidApp
class APODApp : Application() {

    lateinit var db: APODDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, APODDatabase::class.java, "apod.db").build()
    }

}