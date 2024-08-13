package com.myaxa.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myaxa.core.ui.DayNightPreview
import com.myaxa.core.ui.R
import com.myaxa.core.ui.ThemedPreview

@Composable
fun NetworkErrorScreenComponent(
    onRetryClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.bitcoin_image),
            contentDescription = "Cryptocurrency image"
        )
        Text(
            text = "Произошла какая-то ошибка :(\nПопробуем снова?",
            fontSize = 16.sp,
            lineHeight = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 14.dp, bottom = 30.dp)
        )
        Button(
            colors = ButtonColors(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
            shape = RoundedCornerShape(4.dp),
            onClick = onRetryClicked,
        ) {
            Text(
                text = "Попробовать".uppercase(),
                fontSize = 14.sp,
                lineHeight = 14.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@DayNightPreview
@Composable
private fun NetworkErrorScreenComponentPreview() = ThemedPreview {
    NetworkErrorScreenComponent({})
}
