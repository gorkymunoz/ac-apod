package com.gorkymunoz.ac.apod.data.database.source

import com.gorkymunoz.ac.apod.data.database.APODDatabase
import com.gorkymunoz.ac.apod.data.mappers.toDomain
import com.gorkymunoz.ac.apod.data.mappers.toEntity
import com.gorkymunoz.ac.apod.data.source.LocalDataSource
import com.gorkymunoz.ac.apod.domain.APOD
import com.gorkymunoz.ac.apod.framework.dispatcherprovider.DispatcherProvider
import kotlinx.coroutines.withContext
import java.time.LocalDate


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
class APODRoomDataSource(
    private val db: APODDatabase,
    private val dispatchers: DispatcherProvider
) : LocalDataSource {

    override suspend fun getAPOD(): APOD? {
        return withContext(dispatchers.io) {
            db.apodDao().findByDate(LocalDate.now().toString())?.toDomain()
        }
    }

    override suspend fun saveAPOD(apod: APOD) {
        withContext(dispatchers.io) { db.apodDao().insertAPOD(apod.toEntity()) }
    }

    override suspend fun findByDate(date: String): APOD? {
        return withContext(dispatchers.io) { db.apodDao().findByDate(date)?.toDomain() }
    }

    override suspend fun updateAPOD(apod: APOD) {
        return withContext(dispatchers.io) { db.apodDao().updateAPOD(apod.toEntity()) }
    }
}