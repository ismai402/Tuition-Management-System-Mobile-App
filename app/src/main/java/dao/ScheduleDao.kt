package com.example.edututor.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.edututor.data.local.entities.ScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<ScheduleEntity>)

    @Query("SELECT * FROM schedules ORDER BY dateTime ASC")
    fun observeAll(): Flow<List<ScheduleEntity>>

    @Query("DELETE FROM schedules")
    suspend fun clear()
}
