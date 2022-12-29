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
class StockPriceViewModel2 @Inject constructor(
    private val repositoryImpl: RepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val stockName: String = savedStateHandle["value"] ?: ""

    private val _uiState = MutableStateFlow(StockPriceUiState2())
    val uiState2: StateFlow<StockPriceUiState2> = _uiState

    fun getStockPrice(besDt: String, itmsNm: String) {
        viewModelScope.launch {
            _uiState.update {
                StockPriceUiState2(
                    repositoryImpl.getStockPrice(
                        besDt = besDt,
                        itmsNm = itmsNm
                    )
                )
            }
        }
    }
}

data class StockPriceUiState2(
    val data: stock? = null
)