package com.example.currencyapp.ui.home.view

import android.view.View
import com.example.currencyapp.model.response.Currency

interface OnItemClickedListener {

    fun onItemClicked(view : View,currency:Currency)
}