package com.myaxa.features.coin_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.myaxa.core.ui.debugPlaceholder
import com.myaxa.features.coin_details.R
import com.myaxa.features.coin_details.model.CoinDetailsUi
import com.myaxa.core.ui.R as CoreUiR

@Composable
internal fun CoinDetailsComponent(
    model: CoinDetailsUi,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            top = 12.dp,
            bottom = 12.dp + WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding(),
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            AsyncImage(
                model = model.imageUrl,
                placeholder = debugPlaceholder(debugPreview = CoreUiR.drawable.bitcoin_image),
                contentDescription = stringResource(id = CoreUiR.string.cryptocurrency_logo),
                modifier = Modifier.size(90.dp)
            )
        }
        item {
            TextSectionComponent(
                title = stringResource(R.string.description),
                content = model.description
            )
        }
        if (model.categories.isNotEmpty()) {
            item {
                TextSectionComponent(
                    title = stringResource(R.string.categories),
                    content = model.categories
                )
            }
        }
    }
}
