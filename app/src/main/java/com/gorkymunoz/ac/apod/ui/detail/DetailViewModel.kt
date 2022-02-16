package com.gorkymunoz.ac.apod.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorkymunoz.ac.apod.domain.APOD
import com.gorkymunoz.ac.apod.usecases.GetAPODByDate
import com.gorkymunoz.ac.apod.usecases.ToggleFavoriteAPOD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Gorky Muñoz on 18/1/2022.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val apodByDate: GetAPODByDate,
    private val toggleFavorite: ToggleFavoriteAPOD
) :
    ViewModel() {

    private val _apod = MutableLiveData<APOD>()
    val apod: LiveData<APOD>
        get() {
            return _apod
        }

    fun getAPODInformation(date: String) {
        viewModelScope.launch {
            _apod.value = apodByDate.invoke(date)
        }
    }

    fun toggleFavoriteAPOD(apod: APOD){
        viewModelScope.launch {
            toggleFavorite.invoke(apod)
        }
    }
}