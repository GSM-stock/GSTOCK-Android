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
class StockPriceViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val stockName: String = savedStateHandle["value"] ?: ""

    private val _uiState = MutableStateFlow(StockPriceUiState())
    val uiState: StateFlow<StockPriceUiState> = _uiState

    fun getStockPrice(besDt: String, itmsNm: String) {
        viewModelScope.launch {
            _uiState.update {
                StockPriceUiState(
                    repositoryImpl.getStockPrice(
                        besDt = besDt,
                        itmsNm = itmsNm
                    )
                )
            }
        }
    }
}

data class StockPriceUiState(
    val data: stock? = null
)