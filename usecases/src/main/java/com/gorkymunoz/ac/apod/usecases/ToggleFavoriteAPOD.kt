package com.gorkymunoz.ac.apod.usecases

import com.gorkymunoz.ac.apod.data.repository.APODRepository
import com.gorkymunoz.ac.apod.domain.APOD


/**
 * Created by Gorky Muñoz on 18/1/2022.
 */
class ToggleFavoriteAPOD(private val repository: APODRepository) {

    suspend operator fun invoke(apod: APOD): APOD =
        with(apod) {
            copy(isFavorite = !isFavorite).also {
                repository.updateAPOD(it)
            }
        }
}