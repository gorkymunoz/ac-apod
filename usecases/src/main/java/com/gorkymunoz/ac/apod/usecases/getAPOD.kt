package com.gorkymunoz.ac.apod.usecases

import com.gorkymunoz.ac.apod.data.repository.APODRepository


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
class GetAPOD(private val repository: APODRepository) {

    suspend fun invoke() = repository.getAPOD()

}