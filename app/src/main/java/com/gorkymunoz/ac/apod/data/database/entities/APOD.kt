package com.gorkymunoz.ac.apod.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gorkymunoz.ac.apod.domain.MediaType


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
@Entity
data class APOD(
    @PrimaryKey(autoGenerate = false) val date: String,
    val title: String,
    val copyright: String?,
    val explanation: String,
    val mediaType: MediaType,
    val url: String,
    val hdUrl: String?,
    val isFavorite: Boolean
)