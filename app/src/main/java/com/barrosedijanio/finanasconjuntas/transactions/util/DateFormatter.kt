package com.barrosedijanio.finanasconjuntas.transactions.util

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.TimeZone

fun millisecondsToDateString(milliseconds: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val instance = Calendar.getInstance()
    instance.timeZone = TimeZone.getTimeZone("America/Sao_Paulo")

    // Ajustado a mão pois o millisecconds recebido esta sempre com erro de 1 dia
    val oneDay = 86400000
    instance.timeInMillis = milliseconds + oneDay
    return formatter.format(instance.time)
}

fun millisecondsToTimestamp(milliseconds: Long): Timestamp {
    val oneDay = 86400000
    val ofEpochMilli = Instant.ofEpochMilli(milliseconds+oneDay)
    return Timestamp(ofEpochMilli)
}