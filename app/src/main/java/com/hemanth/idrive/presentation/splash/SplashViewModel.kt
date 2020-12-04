package com.hemanth.idrive.presentation.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemanth.idrive.common.Constants
import kotlinx.coroutines.launch

/**
 *<h1>SplashViewModel</h1>
 * @author : Hemanth     hemanthappu006@gmail.com
 * @version : 1.0
 */
class SplashViewModel @ViewModelInject constructor() : ViewModel() {

    private val _time = MutableLiveData<Boolean>()
    val routeEvent: LiveData<Boolean> = _time

    fun initStart() {
        viewModelScope.launch {
            kotlinx.coroutines.delay(Constants.SPLASH_DELAY_MILLIS)
            postRouteEvent(true)
        }
    }

    private fun postRouteEvent(isTime: Boolean) {
        _time.postValue(isTime)
    }
}