package com.myaxa.core.coin.domain

/**
 * Supported currencies for loading cryptocurrencies
 * in the equivalent of this currency
 */
enum class Currency(val symbol: Char) {
    USD('$'), RUB('₽');
}
