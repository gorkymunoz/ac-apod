package com.gorkymunoz.ac.apod.data.repository

import com.gorkymunoz.ac.apod.data.source.LocalDataSource
import com.gorkymunoz.ac.apod.data.source.RemoteDataSource
import com.gorkymunoz.ac.apod.domain.APOD
import java.time.LocalDate


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
class APODRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) {

    suspend fun getAPOD(): APOD {
        return getAPODByDate(LocalDate.now().toString())
    }

    suspend fun getAPODByDate(date: String): APOD {
        val savedAPOD: APOD? = localDataSource.findByDate(date)
        if (savedAPOD != null) {
            return savedAPOD
        }
        val apod = remoteDataSource.getAPODByDate(date)
        localDataSource.saveAPOD(apod)
        return apod
    }

    suspend fun getAPODByDateRange(initialDate: String, endDate: String): List<APOD> {
        return remoteDataSource.getAPODByDateRange(initialDate, endDate)
    }
}