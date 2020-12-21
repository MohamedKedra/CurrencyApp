package com.example.currencyapp.app.base

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel

abstract class BaseViewModel(protected val connectivityManager: ConnectivityManager,) : ViewModel() {

    var requestInProgress = false
        private set

    protected val isNetworkAvailable: Boolean
        get() {
            val activeNetInfo = connectivityManager.activeNetworkInfo
            return if (activeNetInfo != null) {
                activeNetInfo.isAvailable && activeNetInfo.isConnected
            } else {
                false
            }
        }

    fun <T> publishLoading(liveData: LiveDataState<T>) {
        requestInProgress = true
        liveData.postLoading()
    }

    fun <T> publishNoInternet(liveData: LiveDataState<T>) {
        requestInProgress = false
        liveData.postNoInternet()
    }

    fun <T> publishError(liveData: LiveDataState<T>, t: Throwable) {
        requestInProgress = false
        liveData.postError(t)
    }

    fun <T> publishResult(liveData: LiveDataState<T>, data: T) {
        requestInProgress = false
        liveData.postSuccess(data)
    }
}