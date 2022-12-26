package com.example.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.k_stock.R
import java.lang.reflect.Modifier

@Composable
fun MainScreen(viewModel: StockPriceViewModel = hiltViewModel()) {
    viewModel.getStockPrice("20221223", "휴네시온")
    val uiState = viewModel.uiState.collectAsState()
    Row {
        Text(text = uiState.value.data?.response?.body?.items?.item.toString())
    }
}

//@Composable
//fun MainScreen() {
//    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
//        val search_background = R.drawable.search_background
//        val search_icon = R.drawable.ic_baseline_search_24
////        Image(painter = painterResource(id = R.drawable.k_st_remove_svg), contentDescription = null)
////        Image(painter = painterResource(id = search_background), contentDescription = null)
//        Image(painter = painterResource(id = search_icon), contentDescription = null)
//    }
//}