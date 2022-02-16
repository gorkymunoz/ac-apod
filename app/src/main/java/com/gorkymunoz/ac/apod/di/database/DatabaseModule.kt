package com.gorkymunoz.ac.apod.di.database

import android.content.Context
import androidx.room.Room
import com.gorkymunoz.ac.apod.data.database.APODDatabase
import com.gorkymunoz.ac.apod.data.database.dao.APODDao
import com.gorkymunoz.ac.apod.data.database.source.APODRoomDataSource
import com.gorkymunoz.ac.apod.framework.dispatcherprovider.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideAPODRoomDataSource(
        database: APODDatabase,
        dispatchers: DispatcherProvider
    ): APODRoomDataSource {
        return APODRoomDataSource(database, dispatchers)
    }

    @Provides
    fun provideAPODDao(database: APODDatabase): APODDao = database.apodDao()

    @Provides
    @Singleton
    fun provideAPODDatabase(@ApplicationContext appContext: Context): APODDatabase {
        return Room.databaseBuilder(
            appContext,
            APODDatabase::class.java,
            "apod.db"
        ).build()

    }
}