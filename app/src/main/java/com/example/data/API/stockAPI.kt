package com.example.data.API

import com.example.k_stock.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface stockAPI {
    @GET("getStockPriceInfo")
    suspend fun getStockPrice(
        @Query("serviceKey") serviceKey: String = BuildConfig.API_KEY,
        @Query("resultType") resultType: String = "json",
        @Query("basDt") basDt: String? = null,
        @Query("itmsNm") itmsNm: String? = null,
    ): stock
}