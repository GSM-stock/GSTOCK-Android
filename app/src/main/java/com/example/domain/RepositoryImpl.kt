package com.example.domain

import com.example.data.API.stock
import com.example.data.API.stockAPI
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val api: stockAPI): com.example.data.Repository {
    override suspend fun getStockPrice(besDt: String?, itmsNm: String?): stock {
        return api.getStockPrice(basDt = besDt, itmsNm = itmsNm)
    }

}