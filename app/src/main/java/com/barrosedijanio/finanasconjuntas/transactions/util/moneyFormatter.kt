package com.barrosedijanio.finanasconjuntas.transactions.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun convertToBrazilianCurrency(value: Float): String {
    val locale = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(locale).format(value)
}