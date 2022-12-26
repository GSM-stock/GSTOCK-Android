package com.example.data

import com.example.data.API.stock
import com.example.data.API.stockAPI

interface Repository {
    suspend fun getStockPrice(
        besDt: String? = null,
        itmsNm: String? = null
    ): stock
}