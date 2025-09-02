package com.example.edututor.data.repository

import com.example.edututor.data.local.AppDatabase
import com.example.edututor.data.local.entities.ScheduleEntity
import com.example.edututor.data.remote.FirebaseDataSource
import com.example.edututor.data.remote.models.FirebaseSchedule
import com.example.edututor.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ScheduleRepository(
    private val db: AppDatabase,
    private val remote: FirebaseDataSource
) {

    fun observeSchedules(): Flow<List<ScheduleEntity>> = db.scheduleDao().observeAll()

    fun refreshSchedules() = flow<Resource<List<ScheduleEntity>>> {
        emit(Resource.Loading()) // ← Fixed

        val res = remote.fetchSchedules()
        res.fold(onSuccess = { list ->
            val mapped = list.map { ScheduleEntity(it.id, it.title, it.subject, it.dateTime, it.tutorId) }
            db.scheduleDao().clear()
            db.scheduleDao().upsertAll(mapped)
            emit(Resource.Success(mapped))
        }, onFailure = { t ->
            emit(Resource.Error(t.message ?: "Fetch failed"))
        })

    }.catch { e -> emit(Resource.Error(e.message ?: "Unknown error")) }

    suspend fun createSchedule(s: ScheduleEntity): Resource<Unit> {
        val firebase = FirebaseSchedule(s.id, s.title, s.subject, s.dateTime, s.tutorId)
        val res = remote.createSchedule(firebase)
        return res.fold(
            onSuccess = { Resource.Success(Unit) },
            onFailure = { Resource.Error(it.message ?: "Create failed") }
        )
    }
}
