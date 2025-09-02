package com.example.edututor.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Schedule(
    val id: String = "",
    val title: String = "",
    val subject: String = "",
    val dateTime: String = "",
    val tutorId: String = ""
): Parcelable
