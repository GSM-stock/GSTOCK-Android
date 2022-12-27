package com.example.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.example.k_stock.R

@Composable
fun MainScreen(viewModel: StockPriceViewModel = hiltViewModel()) {
    viewModel.getStockPrice("20221226", viewModel.stockName)
    val uiState = viewModel.uiState.collectAsState()
    Column {
        Text(text = uiState.value.data?.response?.body?.items?.item?.get(0)?.clpr.toString())
        Text(text = uiState.value.data?.response?.body?.items?.item?.get(0)?.hipr.toString())
        Text(text = uiState.value.data?.response?.body?.items?.item?.get(0)?.lopr.toString())
        Text(text = uiState.value.data?.response?.body?.items?.item?.get(0)?.mkp.toString())
    }

}



