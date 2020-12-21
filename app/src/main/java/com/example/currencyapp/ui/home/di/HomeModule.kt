package com.example.currencyapp.ui.home.di

import com.example.currencyapp.ui.home.repository.HomeRepository
import com.example.currencyapp.ui.home.view_model.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single {
        HomeRepository(get())
    }

    viewModel {
        HomeViewModel(get(),get(),get())
    }
}