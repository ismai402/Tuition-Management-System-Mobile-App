package com.example.edututor.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedules")
data class ScheduleEntity(
    @PrimaryKey val id: String,
    val title: String,
    val subject: String,
    val dateTime: String,
    val tutorId: String
)
