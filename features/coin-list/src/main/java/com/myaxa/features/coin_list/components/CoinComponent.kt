package com.myaxa.features.coin_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.myaxa.core.ui.debugPlaceholder
import com.myaxa.features.coin_list.model.ListCoinUi
import com.myaxa.features.coin_list.model.PriceChangePercentage
import com.myaxa.core.ui.R as CoreUiR

@Composable
internal fun CoinComponent(
    coin: ListCoinUi,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Row {
            AsyncImage(
                model = coin.imageUrl,
                placeholder = debugPlaceholder(debugPreview = CoreUiR.drawable.bitcoin_image),
                contentDescription = "Coin image",
                modifier = modifier.size(44.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    coin.name,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    coin.symbol.uppercase(),
                    color = MaterialTheme.colorScheme.outline,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = coin.currentPrice,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = coin.priceChangePercentage.value,
                color = coin.priceChangePercentage.color,
                fontSize = 14.sp,
                lineHeight = 14.sp,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CoinComponentPreview() {
    CoinComponent(
        coin = ListCoinUi(
            "bitcoin",
            "Bitcoin",
            "btc",
            "",
            "1000000.0",
            PriceChangePercentage("7.520", Color.DarkGray)
        ),
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}
