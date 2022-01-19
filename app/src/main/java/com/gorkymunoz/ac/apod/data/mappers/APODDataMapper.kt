package com.gorkymunoz.ac.apod.data.mappers

import com.gorkymunoz.ac.apod.domain.MediaType
import com.gorkymunoz.ac.apod.data.database.entities.APOD as APODEntity
import com.gorkymunoz.ac.apod.data.server.model.APOD as APODServer
import com.gorkymunoz.ac.apod.domain.APOD as APODDomain


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
fun APODEntity.toDomain(): APODDomain =
    APODDomain(
        title,
        date,
        copyright,
        explanation,
        mediaType,
        url,
        hdUrl,
        isFavorite
    )

fun APODDomain.toEntity(): APODEntity =
    APODEntity(
        date = date,
        title = title,
        copyright = copyright,
        explanation = explanation,
        mediaType = mediaType,
        url = url,
        hdUrl = hdUrl,
        isFavorite = isFavorite
    )

fun APODServer.toDomain(): APODDomain =
    APODDomain(
        title,
        date,
        copyright,
        explanation,
        MediaType.valueOf(mediaType.uppercase()),
        url,
        hdUrl,
        isFavorite = false
    )