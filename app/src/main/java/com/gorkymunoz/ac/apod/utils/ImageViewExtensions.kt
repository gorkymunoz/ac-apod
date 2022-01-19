package com.gorkymunoz.ac.apod.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


/**
 * Created by Gorky Muñoz on 18/1/2022.
 */

fun ImageView.loadImageAsBitmap(url: String, onResourceReady: (Bitmap?) -> Unit) {
    Glide.with(this)
        .asBitmap()
        .load(url)
        .addListener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Bitmap>?,
                isFirstResource: Boolean
            ): Boolean {
                return e != null
            }

            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: Target<Bitmap>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onResourceReady(resource)
                return false
            }
        })
        .into(this)
}

fun ImageView.loadImage(url: String, listener: RequestListener<Drawable>? = null) {
    Glide.with(this)
        .load(url)
        .addListener(listener)
        .into(this)
}