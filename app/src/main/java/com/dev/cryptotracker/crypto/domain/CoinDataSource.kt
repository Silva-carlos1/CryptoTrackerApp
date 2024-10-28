package com.dev.cryptotracker.crypto.domain

import com.dev.cryptotracker.core.domain.util.NetworkError
import com.dev.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}