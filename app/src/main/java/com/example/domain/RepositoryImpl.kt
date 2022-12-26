package com.example.domain

import com.example.data.API.stock
import com.example.data.API.stockAPI

class RepositoryImpl(val api: stockAPI): com.example.data.Repository {
    override fun getStockPrice(besDt: String?, itmsNm: String?): stock {
        return api.getStockPrice(besDt = besDt, itmsNm = itmsNm)
    }

}