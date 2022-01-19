package com.gorkymunoz.ac.apod.data.source

import com.gorkymunoz.ac.apod.domain.APOD


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
interface LocalDataSource {

    suspend fun getAPOD(): APOD?
    suspend fun saveAPOD(apod: APOD)
    suspend fun findByDate(date: String): APOD?
    suspend fun updateAPOD(apod: APOD)
}