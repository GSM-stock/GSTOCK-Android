package com.example.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.example.k_stock.R
import com.example.k_stock.ui.theme.yellow


@Composable
fun MainScreen(viewModel: StockPriceViewModel = hiltViewModel()) {
    viewModel.getStockPrice("20221226", viewModel.stockName)
    val uiState = viewModel.uiState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(yellow)
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().border(1.dp, color = Color.Black).background(Color.White).height(50.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.padding(start = 15.dp))
            Text(text = uiState.value.data?.response?.body?.items?.item?.get(0)?.itmsNm.toString(), fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 130.dp, end = 150.dp))
        }
        
        Spacer(modifier = Modifier.height( 46.dp))
        
        Text(text = "주식데이터는 영업일 기준 하루 전 데이터입니다.")
        Text(text = uiState.value.data?.response?.body?.items?.item?.get(0)?.clpr.toString(), modifier = Modifier.border(1.dp, Color.Black).padding(horizontal = 125.dp, vertical = 15.dp).background(Color.White))
        Text(text = uiState.value.data?.response?.body?.items?.item?.get(0)?.hipr.toString())
        Text(text = uiState.value.data?.response?.body?.items?.item?.get(0)?.lopr.toString())
        Text(text = uiState.value.data?.response?.body?.items?.item?.get(0)?.mkp.toString())
    }

}





