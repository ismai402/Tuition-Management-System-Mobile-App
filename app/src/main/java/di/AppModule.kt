package com.example.edututor.di

import android.content.Context
import androidx.room.Room
import com.example.edututor.data.local.AppDatabase
import com.example.edututor.data.remote.FirebaseDataSource
import com.example.edututor.data.repository.AttendanceRepository
import com.example.edututor.data.repository.ScheduleRepository
import com.example.edututor.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object AppModule {
    private lateinit var appDb: AppDatabase
    private lateinit var remote: FirebaseDataSource
    private lateinit var userRepo: UserRepository
    private lateinit var scheduleRepo: ScheduleRepository
    private lateinit var attendanceRepo: AttendanceRepository

    fun init(context: Context) {
        appDb = Room.databaseBuilder(context, AppDatabase::class.java, "edututor.db").build()
        remote = FirebaseDataSource(FirebaseAuth.getInstance(), FirebaseFirestore.getInstance())
        userRepo = UserRepository(appDb, remote)
        scheduleRepo = ScheduleRepository(appDb, remote)
        attendanceRepo = AttendanceRepository(appDb, remote)
    }

    fun db() = appDb
    fun users() = userRepo
    fun schedules() = scheduleRepo
    fun attendance() = attendanceRepo
}
