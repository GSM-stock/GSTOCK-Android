package com.example.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.API.stock
import com.example.domain.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockPriceViewModel6 @Inject constructor(
    private val repositoryImpl: RepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val stockName: String = savedStateHandle["value"] ?: ""

    private val _uiState = MutableStateFlow(StockPriceUiState6())
    val uiState6: StateFlow<StockPriceUiState6> = _uiState

    fun getStockPrice(besDt: String, itmsNm: String) {
        viewModelScope.launch {
            _uiState.update {
                StockPriceUiState6(
                    repositoryImpl.getStockPrice(
                        besDt = besDt,
                        itmsNm = itmsNm
                    )
                )
            }
        }
    }
}

data class StockPriceUiState6(
    val data: stock? = null
)