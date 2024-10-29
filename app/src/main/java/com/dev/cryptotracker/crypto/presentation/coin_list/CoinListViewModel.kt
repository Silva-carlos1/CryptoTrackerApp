package com.dev.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.cryptotracker.core.domain.util.onError
import com.dev.cryptotracker.core.domain.util.onSuccess
import com.dev.cryptotracker.crypto.domain.CoinDataSource
import com.dev.cryptotracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
): ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: CoinListActions) {
        when(action) {
            is CoinListActions.OnCoinClick -> {

            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            coinDataSource
                .getCoins()
                .onSuccess { coins ->
                    _state.update { it.copy(
                        isLoading = false,
                        coins = coins.map { coin -> coin.toCoinUi() }
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(CoinListEvent.Error(error))
                }
        }
    }
}