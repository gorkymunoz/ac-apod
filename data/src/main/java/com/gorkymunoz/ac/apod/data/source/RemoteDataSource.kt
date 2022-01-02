package com.gorkymunoz.ac.apod.data.source

import com.gorkymunoz.ac.apod.domain.APOD


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
interface RemoteDataSource {

    suspend fun getAPOD(): APOD
    suspend fun getAPODByDate(date: String): APOD
    suspend fun getAPODByDateRange(initialDate: String, endDate: String): List<APOD>
}