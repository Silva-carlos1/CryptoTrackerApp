package com.dev.cryptotracker.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.dev.cryptotracker.crypto.presentation.models.CoinUi

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi>,
    val selectedCoin: CoinUi? = null
)