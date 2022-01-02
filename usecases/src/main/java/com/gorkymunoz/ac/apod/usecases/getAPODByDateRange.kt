package com.gorkymunoz.ac.apod.usecases

import com.gorkymunoz.ac.apod.data.repository.APODRepository
import java.time.LocalDate


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
class GetAPODByDateRange(private val repository: APODRepository) {

    suspend operator fun invoke(initialDate: LocalDate, endDate: LocalDate) =
        repository.getAPODByDateRange(
            initialDate = initialDate.toString(),
            endDate = endDate.toString()
        )
}