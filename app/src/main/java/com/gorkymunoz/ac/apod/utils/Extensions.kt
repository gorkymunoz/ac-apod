package com.gorkymunoz.ac.apod.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */

inline fun <reified T : ViewModel> Fragment.getVMFactory(crossinline factory: () -> T):
        ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

}