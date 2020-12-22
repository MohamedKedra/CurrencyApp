package com.example.currencyapp.app.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.currencyapp.model.network.ApiService
import com.example.currencyapp.model.network.UnsafeOkHttpClient
import com.example.currencyapp.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder().baseUrl(Constants.API.BASE_URL)
//            .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()


    }

//    fun getUnsafeOkHttpClient(): OkHttpClient {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val builder = OkHttpClient.Builder()
//        builder.addInterceptor(interceptor)
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .followRedirects(true)
//            .followSslRedirects(true)
//            .addInterceptor { chain ->
//                val newRequest = chain.request().newBuilder()
//                    .addHeader("Authorization", UUID.randomUUID().toString())
//                    .build()
//                chain.proceed(newRequest)
//            }
//    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    single {
        get<Context>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}