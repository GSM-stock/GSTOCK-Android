package com.example.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.example.k_stock.R
import com.example.k_stock.ui.theme.gray
import com.example.k_stock.ui.theme.hipr
import com.example.k_stock.ui.theme.lopr
import com.example.k_stock.ui.theme.yellow


@Composable
fun MainScreen(viewModel: StockPriceViewModel = hiltViewModel()) {
    viewModel.getStockPrice("20221227", viewModel.stockName)
    val uiState = viewModel.uiState.collectAsState()
    val expanded = remember { mutableStateOf(false) }
    val xPadding = if (expanded.value) 380.dp else 360.dp
    val yPadding = if (expanded.value) 520.dp else 310.dp

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
                .border(1.dp, color = Color.Black)
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
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }


        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier
                .background(Color.White)
        ) {
            testChart(modifier = Modifier.size(xPadding, yPadding))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .width(360.dp)
                .background(Color.White)
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "주식데이터는 영업일 기준 하루 전 데이터입니다.",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
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





