package com.gorkymunoz.ac.apod.usecases

import com.gorkymunoz.ac.apod.data.repository.APODRepository
import com.gorkymunoz.ac.apod.domain.APOD


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
class getAPOD(private val repository: APODRepository) {

    suspend operator fun invoke(): APOD {
        return repository.getAPOD()
    }
}