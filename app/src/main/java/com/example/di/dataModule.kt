package com.example.di

import com.example.data.API.stock
import com.example.data.API.stockAPI
import com.example.data.Repository
import com.example.domain.RepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object dataModule {
    val gson: Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideRetrofit(): stockAPI {
        return Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(provideOkhttpClient())
            .build()
            .create(stockAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: stockAPI): Repository {
        return RepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val httpInterceptor = HttpLoggingInterceptor()
        httpInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(httpInterceptor).build()
    }
}