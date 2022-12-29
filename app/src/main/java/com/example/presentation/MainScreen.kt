package com.example.presentation

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.example.k_stock.R
import com.example.k_stock.ui.theme.*


@Composable
fun MainScreen(
    viewModel: StockPriceViewModel = hiltViewModel(),
    viewModel1: StockPriceViewModel1 = hiltViewModel(),
    viewModel2: StockPriceViewModel2 = hiltViewModel(),
    viewModel3: StockPriceViewModel3 = hiltViewModel(),
    viewModel4: StockPriceViewModel4 = hiltViewModel(),
    viewModel5: StockPriceViewModel5 = hiltViewModel(),
    viewModel6: StockPriceViewModel6 = hiltViewModel()
) {
    viewModel.getStockPrice("20221227", viewModel.stockName)
    viewModel1.getStockPrice("20221226", viewModel1.stockName)
    viewModel2.getStockPrice("20221223", viewModel2.stockName)
    viewModel3.getStockPrice("20221222", viewModel3.stockName)
    viewModel4.getStockPrice("20221221", viewModel4.stockName)
    viewModel5.getStockPrice("20221220", viewModel5.stockName)
    viewModel6.getStockPrice("20221219", viewModel6.stockName)

    val uiState = viewModel.uiState.collectAsState()
    val uiState1 = viewModel1.uiState1.collectAsState()
    val uiState2 = viewModel2.uiState2.collectAsState()
    val uiState3 = viewModel3.uiState3.collectAsState()
    val uiState4 = viewModel4.uiState4.collectAsState()
    val uiState5 = viewModel5.uiState5.collectAsState()
    val uiState6 = viewModel6.uiState6.collectAsState()

    val expanded = remember { mutableStateOf(false) }
    val price = uiState.value.data?.response?.body?.items?.item?.get(0)?.clpr?.toFloat() ?: 1F
    val price1 = uiState1.value.data?.response?.body?.items?.item?.get(0)?.clpr?.toFloat() ?: 2F
    val price2 = uiState2.value.data?.response?.body?.items?.item?.get(0)?.clpr?.toFloat() ?: 3F
    val price3 = uiState3.value.data?.response?.body?.items?.item?.get(0)?.clpr?.toFloat() ?: 4F
    val price4 = uiState4.value.data?.response?.body?.items?.item?.get(0)?.clpr?.toFloat() ?: 5F
    val price5 = uiState5.value.data?.response?.body?.items?.item?.get(0)?.clpr?.toFloat() ?: 6F
    val price6 = uiState6.value.data?.response?.body?.items?.item?.get(0)?.clpr?.toFloat() ?: 7F

    val yh : List<Float?> = listOf(
          price6, price5, price4, price3, price2, price1, price
    )

    val xPadding by animateDpAsState (
        if (expanded.value) 380.dp else 360.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    val yPadding by animateDpAsState (
        if (expanded.value) 520.dp else 310.dp,
        animationSpec = tween(
            durationMillis = 900
        )
    )

    val dePadding by animateDpAsState(
        if (expanded.value) 0.dp else 360.dp,
        animationSpec = tween(
            durationMillis = 800
        )
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(yellow)
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = gray)
                .background(Color.White)
                .height(50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.padding(start = 15.dp)
            )
            Text(
                text = uiState.value.data?.response?.body?.items?.item?.get(0)?.itmsNm.toString(),
                modifier = Modifier.width(335.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .width(xPadding)
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            ElevatedButton(onClick = { expanded.value = !expanded.value }) {
                Text(if (expanded.value) "Show less" else "Show more", color = Color.Black)
            }
        }


        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier
                .background(Color.White)
        ) {
            Chart(modifier = Modifier.size(xPadding, yPadding), yh = yh)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .width(dePadding)
                .background(Color.White)
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "주식데이터는 영업일 기준 하루 전 데이터입니다.",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row() {
                Text(text = "현재가 | ", fontSize = 20.sp)
                Text(
                    text = uiState.value.data?.response?.body?.items?.item?.get(0)?.clpr.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row() {
                Text(text = "상한가 | ", fontSize = 20.sp)
                Text(
                    text = uiState.value.data?.response?.body?.items?.item?.get(0)?.hipr.toString(),
                    fontSize = 20.sp,
                    color = hipr,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row() {
                Text(text = "하한가 | ", fontSize = 20.sp)
                Text(
                    text = uiState.value.data?.response?.body?.items?.item?.get(0)?.lopr.toString(),
                    fontSize = 20.sp,
                    color = lopr,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row() {
                Text(text = "평균가 | ", fontSize = 20.sp)
                Text(
                    text = uiState.value.data?.response?.body?.items?.item?.get(0)?.mkp.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}




