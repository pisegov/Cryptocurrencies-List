package com.myaxa.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myaxa.core.ui.theme.ProgressIndicatorColor

@Composable
fun ProgressIndicatorComponent(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        CircularProgressIndicator(
            color = ProgressIndicatorColor,
            modifier = Modifier.size(44.dp),
        )
    }
}