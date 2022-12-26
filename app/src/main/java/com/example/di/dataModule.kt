package com.example.di

import com.example.data.API.stock
import com.example.data.API.stockAPI
import com.example.data.Repository
import com.example.domain.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object dataModule  {
    @Singleton
    @Provides
    fun provideRetrofit(): stockAPI {
        return Retrofit.Builder()
            .baseUrl("https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(stockAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: stockAPI): Repository {
        return RepositoryImpl(api)
    }
}