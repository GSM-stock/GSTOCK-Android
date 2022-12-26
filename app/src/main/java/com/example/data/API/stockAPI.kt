package com.example.data.API

import com.example.k_stock.BuildConfig
import retrofit2.Retrofit
import retrofit2.http.GET

interface stockAPI {
    @GET("/getStockPriceInfo")
    fun getStockPrice(
        serviceKey: String = BuildConfig.API_KEY,
        resultType: String = "json",
        besDt: String? = null,
        itmsNm: String? = null,
    ): stock

}