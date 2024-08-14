package com.myaxa.core.ui.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import javax.inject.Inject

/**
 * Formats decimal to formatted string
 * For example: 61215 -> 61,215.00
 */
class DecimalStringFormatter @Inject constructor() {

    private val pattern = "#,##0.00"
    private val symbols = DecimalFormatSymbols(Locale.US)

    fun formatDoubleToString(number: Double): String =
        DecimalFormat(pattern, symbols).format(number)
}
