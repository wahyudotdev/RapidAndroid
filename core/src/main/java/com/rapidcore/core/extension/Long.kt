package com.rapidcore.core.extension

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun Long.toCurrency(): String {
    val format = DecimalFormat("###,###,###")
    return "Rp.${format.format(this)}"
}

fun Long.toDate(): String {
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale("id", "ID"))
    return formatter.format(Date())
}

fun Long.toDateTime(): String{
    val formatter = SimpleDateFormat("dd MMM yyyy HH:mm", Locale("id", "ID"))
    return formatter.format(Date())
}