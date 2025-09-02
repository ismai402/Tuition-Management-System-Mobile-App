package com.example.edututor.data.repository

import com.example.edututor.data.local.AppDatabase
import com.example.edututor.data.local.entities.AttendanceEntity
import com.example.edututor.data.remote.FirebaseDataSource
import com.example.edututor.data.remote.models.FirebaseAttendance
import com.example.edututor.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class AttendanceRepository(
    private val db: AppDatabase,
    private val remote: FirebaseDataSource
) {

    fun observeBySchedule(scheduleId: String): Flow<List<AttendanceEntity>> =
        db.attendanceDao().observeBySchedule(scheduleId)

    fun refreshBySchedule(scheduleId: String) = flow<Resource<List<AttendanceEntity>>> {
        emit(Resource.Loading()) // ← Fixed

        val res = remote.fetchAttendanceBySchedule(scheduleId)
        res.fold(onSuccess = { list ->
            list.forEach { a ->
                db.attendanceDao().upsert(
                    AttendanceEntity(a.id, a.scheduleId, a.studentId, a.status, a.timestamp)
                )
            }
            emit(
                Resource.Success(
                    list.map { AttendanceEntity(it.id, it.scheduleId, it.studentId, it.status, it.timestamp) }
                )
            )
        }, onFailure = { t ->
            emit(Resource.Error(t.message ?: "Fetch failed"))
        })

    }.catch { e -> emit(Resource.Error(e.message ?: "Unknown error")) }

    suspend fun mark(att: AttendanceEntity): Resource<Unit> {
        val fa = FirebaseAttendance(att.id, att.scheduleId, att.studentId, att.status, att.timestamp)
        val res = remote.markAttendance(fa)
        return res.fold(
            onSuccess = { Resource.Success(Unit) },
            onFailure = { Resource.Error(it.message ?: "Mark failed") }
        )
    }

    fun totalByStudent(studentId: String) = db.attendanceDao().totalByStudent(studentId)
    fun presentByStudent(studentId: String) = db.attendanceDao().presentByStudent(studentId)
}
