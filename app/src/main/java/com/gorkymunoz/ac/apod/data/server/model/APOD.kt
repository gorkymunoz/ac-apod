package com.gorkymunoz.ac.apod.data.server.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
data class APOD(
    val title: String,
    val date: String,
    val copyright: String?,
    val explanation: String,
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("service_version") val serviceVersion: String,
    val url: String,
    @SerializedName("hdurl") val hdUrl: String?
)