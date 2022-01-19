package com.gorkymunoz.ac.apod.data.server.source

import com.gorkymunoz.ac.apod.data.mappers.toDomain
import com.gorkymunoz.ac.apod.data.server.api.APODApi
import com.gorkymunoz.ac.apod.data.source.RemoteDataSource
import com.gorkymunoz.ac.apod.domain.APOD
import com.gorkymunoz.ac.apod.framework.dispatcherprovider.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
class NasaAPODDataSource(
    private val service: APODApi,
    private val dispatchers: DispatcherProvider,
    private val apiKey: String
) :
    RemoteDataSource {

    override suspend fun getAPOD(): APOD = withContext(dispatchers.io) {
        service.getAPOD(apiKey = apiKey).toDomain()
    }

    override suspend fun getAPODByDate(date: String): APOD =
        withContext(dispatchers.io) {
            service.getAPOD(date, apiKey).toDomain()
        }

    override suspend fun getAPODByDateRange(initialDate: String, endDate: String): List<APOD> =
        withContext(dispatchers.io) {
            service.getAPODsByDateRange(initialDate, endDate, apiKey).map { it.toDomain() }
        }
}