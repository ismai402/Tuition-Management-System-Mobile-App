package com.example.edututor.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendance")
data class AttendanceEntity(
    @PrimaryKey val id: String,
    val scheduleId: String,
    val studentId: String,
    val status: String,
    val timestamp: String
)
