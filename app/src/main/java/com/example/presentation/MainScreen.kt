package com.example.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(viewModel: StockPriceViewModel = hiltViewModel()) {
    Row {
        Text(text = viewModel.getStockPrice(besDt = "20221223", itmsNm = "휴네시온").toString())
    }
}