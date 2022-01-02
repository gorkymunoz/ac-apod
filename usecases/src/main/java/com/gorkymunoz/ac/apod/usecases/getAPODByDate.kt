package com.gorkymunoz.ac.apod.usecases

import com.gorkymunoz.ac.apod.data.repository.APODRepository
import java.time.LocalDate


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
class getAPODByDate(private val repository: APODRepository) {

    suspend operator fun invoke(date: LocalDate) = repository.getAPODByDate(date.toString())
}