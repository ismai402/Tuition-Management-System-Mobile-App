package com.example.edututor.model

data class Performance(
    val studentId: String,
    val totalClasses: Int,
    val presentCount: Int
) {
    val attendanceRate: Double get() = if (totalClasses == 0) 0.0 else presentCount * 100.0 / totalClasses
}
