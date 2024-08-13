package com.myaxa.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Snackbar(
    snackbarData: SnackbarData,
    containerColor: Color = MaterialTheme.colorScheme.errorContainer
) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = containerColor,
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = snackbarData.visuals.message,
            color = MaterialTheme.colorScheme.onError,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}
