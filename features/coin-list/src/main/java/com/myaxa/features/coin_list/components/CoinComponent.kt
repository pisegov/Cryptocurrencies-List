package com.myaxa.features.coin_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.myaxa.core.ui.theme.Gray
import com.myaxa.core.ui.theme.TitleColor
import com.myaxa.features.coin_list.model.ListCoinUi
import com.myaxa.features.coin_list.model.PriceChangePercentage

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
                contentDescription = "Coin image",
                modifier
                    .size(44.dp, 44.dp)
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
                    color = TitleColor
                )
                Text(
                    coin.symbol.uppercase(),
                    color = Gray,
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
                color = TitleColor
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
        ListCoinUi(
            "bitcoin",
            "Bitcoin",
            "btc",
            "https://coin-images.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
            "1000000.0",
            PriceChangePercentage("7.520", Color.DarkGray)
        ),
    )
}
