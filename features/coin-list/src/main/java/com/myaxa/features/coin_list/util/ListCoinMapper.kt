package com.myaxa.features.coin_list.util

import androidx.compose.ui.graphics.Color
import com.myaxa.core.coin.domain.Currency
import com.myaxa.core.coin.domain.ListCoin
import com.myaxa.core.ui.theme.DarkGreen
import com.myaxa.core.ui.theme.Red
import com.myaxa.core.ui.util.DecimalStringFormatter
import com.myaxa.features.coin_list.model.ListCoinUi
import com.myaxa.features.coin_list.model.PriceChangePercentage
import javax.inject.Inject
import kotlin.math.abs

internal class ListCoinMapper @Inject constructor(
    private val decimalFormatter: DecimalStringFormatter,
) {

    fun mapToUiModel(model: ListCoin, currency: Currency): ListCoinUi = with(model) {

        return ListCoinUi(
            id = id,
            name = name,
            symbol = symbol.uppercase(),
            imageUrl = image,
            currentPrice = composeCurrentPriceString(number = currentPrice, currency = currency),
            priceChangePercentage = composePriceChangePercentageModel(number = priceChangePercentage)
        )
    }

    private fun composeCurrentPriceString(number: Double, currency: Currency): String =
        "${currency.symbol} ${decimalFormatter.formatDoubleToString(number)}"

    private fun composePriceChangePercentageModel(number: Double): PriceChangePercentage {
        val sign = when {
            (number > 0) -> "+ "
            (number < 0) -> "- "
            else -> ""
        }

        val string = "$sign${decimalFormatter.formatDoubleToString(abs(number))}%"

        val color: Color = when {
            (number > 0) -> DarkGreen
            (number < 0) -> Red
            else -> Color.Gray
        }

        return PriceChangePercentage(value = string, color = color)
    }
}
