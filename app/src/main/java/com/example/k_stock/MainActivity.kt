package com.example.k_stock

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.k_stock.ui.theme.KSTOCKTheme
import com.example.presentation.MainScreen
import com.example.presentation.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KSTOCKTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "search_screen") {
                    composable("search_screen") { SearchScreen(navController) }
                    composable("main_screen/{value}") { MainScreen() }
                }
            }
        }
    }
}
