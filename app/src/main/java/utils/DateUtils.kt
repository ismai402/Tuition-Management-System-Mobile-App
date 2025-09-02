package com.example.edututor.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    fun nowString(): String = sdf.format(Date())
    fun format(date: Date): String = sdf.format(date)
}
