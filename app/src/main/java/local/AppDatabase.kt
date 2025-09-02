package com.example.edututor.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.edututor.data.local.dao.AttendanceDao
import com.example.edututor.data.local.dao.ScheduleDao
import com.example.edututor.data.local.dao.UserDao
import com.example.edututor.data.local.entities.AttendanceEntity
import com.example.edututor.data.local.entities.ScheduleEntity
import com.example.edututor.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class, ScheduleEntity::class, AttendanceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun attendanceDao(): AttendanceDao
}
