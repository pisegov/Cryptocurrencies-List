package com.myaxa.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val Gray = Color(0xFF9B9B9B)
val DarkGray = Color(0xFF525252)
val DarkGreen = Color(0xFF2A9D8F)
val Red = Color(0xFFEB5757)
val Orange = Color(0xFFF7931A)
val PearlLusta = Color(0xFFFBEFDC)
val SeaBuckHorn = Color(0xFFFEBC4F)
val Alto = Color(0xFFE0E0E0)

val primaryLight = Orange
val onPrimaryLight = White
val primaryContainerLight = PearlLusta
val onPrimaryContainerLight = SeaBuckHorn
val errorContainerLight = Red
val onErrorContainerLight = onPrimaryLight
val onSurfaceVariantLight = DarkGray
val outlineLight = Gray
val surfaceContainerHighestLight = Alto

val primaryDark = Color(0xFFFABA72)
val onPrimaryDark = Color(0xFF482900)
val primaryContainerDark = Color(0xFF673D00)
val onPrimaryContainerDark = Color(0xFFFFDDBA)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val onSurfaceVariantDark = Color(0xFFD4C4B5)
val outlineDark = Color(0xFF9D8E81)
val surfaceContainerHighestDark = Color(0xFF3B332C)

val Color.Companion.Link: Color
    @Composable
    get(): Color = if (isSystemInDarkTheme()) Color(0xFF10AAAA) else Blue
