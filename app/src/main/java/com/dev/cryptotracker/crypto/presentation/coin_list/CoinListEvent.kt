package com.dev.cryptotracker.crypto.presentation.coin_list

import com.dev.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}