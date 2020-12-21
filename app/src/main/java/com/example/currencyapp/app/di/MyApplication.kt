package com.example.currencyapp.app.di

import android.app.Application
import com.example.currencyapp.app.di.appModule
import com.example.currencyapp.ui.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    appModule,
                    homeModule
                )
            )
        }
    }
}