package com.example.presentation

import androidx.lifecycle.ViewModel
import com.example.domain.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StockPriceViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl): ViewModel() {
    fun getStockPrice(besDt: String, itmsNm: String) {
        repositoryImpl.getStockPrice(besDt = besDt, itmsNm =  itmsNm)
    }
}