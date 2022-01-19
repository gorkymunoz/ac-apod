package com.gorkymunoz.ac.apod.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorkymunoz.ac.apod.domain.APOD
import com.gorkymunoz.ac.apod.usecases.GetAPOD
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAPODUseCase: GetAPOD
) : ViewModel() {

    private val _apod = MutableLiveData<APOD>()
    val apod: LiveData<APOD>
        get() {
            if (_apod.value == null) getAPOD()
            return _apod
        }

    private fun getAPOD() {
        viewModelScope.launch {
            _apod.value = getAPODUseCase.invoke()
        }
    }
}