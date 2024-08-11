package com.myaxa.features.coin_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myaxa.core.coin.domain.Currency
import com.myaxa.core.ui.theme.Alto
import com.myaxa.core.ui.theme.PearlLusta
import com.myaxa.core.ui.theme.SeaBuckHorn

@Composable
internal fun CurrencyButtonComponent(
    currency: Currency,
    isSelected: Boolean,
    onClick: (Currency) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(100),
        colors = CardColors(
            Alto,
            MaterialTheme.colorScheme.onBackground,
            PearlLusta,
            SeaBuckHorn,
        ),
        onClick = { onClick(currency) },
        modifier = modifier,
        enabled = !isSelected
    ) {
        Text(
            text = currency.name,
            Modifier.padding(vertical = 8.dp, horizontal = 28.dp)
        )
    }
}
