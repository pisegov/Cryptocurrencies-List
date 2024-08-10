package com.myaxa.features.coin_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myaxa.core.coin.domain.Currency

@Composable
internal fun CoinListToolbarComponent(
    currentCurrency: Currency,
    onCurrencySelected: (Currency) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shadowElevation = 10.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(WindowInsets.statusBars.asPaddingValues()),
        ) {
            Text(
                text = "Список криптовалют",
                fontSize = 20.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = modifier.padding(16.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 6.dp)
            ) {
                Currency.entries.forEach {
                    item {
                        CurrencyButtonComponent(
                            currency = it,
                            isSelected = currentCurrency == it,
                            onClick = onCurrencySelected,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CoinListToolbarPreview() {
    CoinListToolbarComponent(currentCurrency = Currency.USD, {})
}
