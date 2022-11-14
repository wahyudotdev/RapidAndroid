package com.rapidcore.core.extension

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@Suppress("unused")
fun Long.toCurrency(): String {
    val format = DecimalFormat("###,###,###")
    return "Rp.${format.format(this)}"
}

@Suppress("unused")
fun Long.toDate(): String {
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale("id", "ID"))
    return formatter.format(Date())
}

@Suppress("unused")
fun Long.toDateTime(): String{
    val formatter = SimpleDateFormat("dd MMM yyyy HH:mm", Locale("id", "ID"))
    return formatter.format(Date())
}