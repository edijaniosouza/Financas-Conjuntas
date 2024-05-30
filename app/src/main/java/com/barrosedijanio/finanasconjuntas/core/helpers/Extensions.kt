package com.barrosedijanio.finanasconjuntas.core.helpers

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat

fun stringToTimestamp(timestamp: String): Timestamp {

    lateinit var converted : Timestamp
    try {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val date = formatter.parse(timestamp)
        converted = Timestamp(date)
    }catch (e: Exception){
        e.printStackTrace()
    }

    return converted
}