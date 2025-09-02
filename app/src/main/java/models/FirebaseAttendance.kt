package com.example.edututor.data.remote.models

data class FirebaseAttendance(
    val id: String = "",
    val scheduleId: String = "",
    val studentId: String = "",
    val status: String = "present",
    val timestamp: String = ""
)
