package com.hemanth.idrive.presentation.webUi

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import com.hemanth.idrive.base.BaseViewModel

/**
 *<h1>WebViewModel</h1>
 * @author : Hemanth     hemanthappu006@gmail.com
 * @version : 1.0
 */
class WebViewModel @ViewModelInject constructor() : BaseViewModel() {

    val loading = ObservableBoolean(true)
}