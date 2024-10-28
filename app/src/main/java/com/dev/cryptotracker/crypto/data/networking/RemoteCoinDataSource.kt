package com.dev.cryptotracker.crypto.data.networking

import com.dev.cryptotracker.core.data.networking.constructUrl
import com.dev.cryptotracker.core.data.networking.safeCall
import com.dev.cryptotracker.core.domain.util.NetworkError
import com.dev.cryptotracker.core.domain.util.Result
import com.dev.cryptotracker.core.domain.util.map
import com.dev.cryptotracker.crypto.data.mappers.toCoin
import com.dev.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.dev.cryptotracker.crypto.domain.Coin
import com.dev.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}