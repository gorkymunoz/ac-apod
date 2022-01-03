package com.gorkymunoz.ac.apod.ui.customviews

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.gorkymunoz.ac.apod.R


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
class RoundedBottomNavigationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : BottomNavigationView(context, attrs) {

    init {
        changeCornerRadius()
    }

    private fun changeCornerRadius() {
        val navViewBackground: MaterialShapeDrawable = background as MaterialShapeDrawable
        val radius = resources.getDimension(R.dimen.bottom_navigation_radius)
        navViewBackground.shapeAppearanceModel = navViewBackground.shapeAppearanceModel
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, radius)
            .build()
    }
}