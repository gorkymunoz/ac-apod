package com.gorkymunoz.ac.apod.data.server.api

import com.gorkymunoz.ac.apod.data.server.model.APOD
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
interface APODApi {

    @GET("planetary/apod")
    suspend fun getAPOD(
        @Query("date") date: String? = null,
        @Query("api_key") apiKey: String,
    ): APOD

    @GET("planetary/apod")
    suspend fun getAPODsByDateRange(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String,
    ): List<APOD>

    @GET("planetary/apod")
    suspend fun getRandomAPODs(
        @Query("count") count: Int,
        @Query("api_key") apiKey: String,
    ): List<APOD>

}