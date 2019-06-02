package com.android.weathergojek.screen

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */

fun String.formatDateDay(): String = this.formatDate("EEEE")

fun String.formatDate(toFormat: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val date = dateFormat.parse(this)
    val dateFormatTo = SimpleDateFormat(toFormat, Locale.ENGLISH)
    return dateFormatTo.format(date)
}

inline fun <T : Any> T?.notNull(f: (it: T) -> Unit) {
    if(this != null) f(this)
}