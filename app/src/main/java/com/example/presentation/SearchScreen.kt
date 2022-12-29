package com.example.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.k_stock.R
import com.example.k_stock.ui.theme.yellow

@Composable
fun SearchScreen(navController: NavController) {
    var value by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(yellow)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(160.dp))
        Image(
            painter = painterResource(
                id = R.drawable.k_stock_logo
            ),
            contentDescription = null,
            modifier = Modifier
                .width(250.dp)
                .height(240.dp)
                .align(CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(80.dp))
        BasicTextField(
            modifier = Modifier.align(CenterHorizontally),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                navController.navigate("main_screen/$value")
            }),
            value = value,
            onValueChange = { value = it },
            decorationBox = {
                Row(
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(10.dp))
                        .width(250.dp)
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_search_24),
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp)
                    )
                    it()
                }
            }
        )
    }
}