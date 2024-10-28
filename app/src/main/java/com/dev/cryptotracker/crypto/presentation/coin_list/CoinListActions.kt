package com.dev.cryptotracker.crypto.presentation.coin_list

import com.dev.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListActions {
    data class OnCoinClick(val coinUi: CoinUi): CoinListActions
}