package com.example.k_stock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
                    composable("main_screen/{value}") { MainScreen(navController = navController) }
                }
            }
        }
    }
}
