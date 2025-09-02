package com.example.edututor.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attendance(
    val id: String = "",
    val scheduleId: String = "",
    val studentId: String = "",
    val status: String = "present",
    val timestamp: String = ""
): Parcelable
