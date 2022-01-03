package com.gorkymunoz.ac.apod.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gorkymunoz.ac.apod.data.database.dao.APODDao
import com.gorkymunoz.ac.apod.data.database.entities.APOD


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
@Database(entities = [APOD::class], version = 1)
abstract class APODDatabase : RoomDatabase() {

    abstract fun apodDao(): APODDao
}
