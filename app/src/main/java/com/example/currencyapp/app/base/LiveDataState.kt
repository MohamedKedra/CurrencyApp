package com.example.currencyapp.app.base

import androidx.lifecycle.MutableLiveData

class LiveDataState<T> : MutableLiveData<DataState<T>>() {

    fun postLoading(){
        postValue(DataState<T>().getLoading())
    }

    fun postSuccess(data : T){
        postValue(DataState<T>().getSuccess(data))
    }

    fun postError(t : Throwable) {
        postValue(DataState<T>().getError(t))
    }

    fun postNoInternet() {
        postValue(DataState<T>().getNoInternet())
    }
}