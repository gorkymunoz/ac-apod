package com.gorkymunoz.ac.apod.domain

data class APOD(
    val title: String,
    val date: String,
    val copyright: String?,
    val explanation: String,
    val mediaType: MediaType,
    val url: String,
    val hdUrl: String?,
    val isFavorite: Boolean
)