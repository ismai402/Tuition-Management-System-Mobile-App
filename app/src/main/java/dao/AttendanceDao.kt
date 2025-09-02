package com.example.edututor.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.edututor.data.local.entities.AttendanceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(att: AttendanceEntity)

    @Query("SELECT * FROM attendance WHERE scheduleId = :scheduleId")
    fun observeBySchedule(scheduleId: String): Flow<List<AttendanceEntity>>

    @Query("SELECT COUNT(*) FROM attendance WHERE studentId = :studentId")
    fun totalByStudent(studentId: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM attendance WHERE studentId = :studentId AND status = 'present'")
    fun presentByStudent(studentId: String): Flow<Int>
}
