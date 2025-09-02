package com.example.edututor.data.remote

import com.example.edututor.data.remote.models.FirebaseAttendance
import com.example.edututor.data.remote.models.FirebaseSchedule
import com.example.edututor.data.remote.models.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseDataSource(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) {
    suspend fun register(name: String, email: String, password: String, role: String): Result<FirebaseUser> = try {
        auth.createUserWithEmailAndPassword(email, password).await()
        val uid = auth.currentUser?.uid ?: ""
        val u = FirebaseUser(uid, name, email, role)
        db.collection("users").document(uid).set(u).await()
        Result.success(u)
    } catch (t: Throwable) {
        Result.failure(t)
    }

    suspend fun login(email: String, password: String): Result<FirebaseUser> = try {
        auth.signInWithEmailAndPassword(email, password).await()
        val uid = auth.currentUser?.uid ?: ""
        val snap = db.collection("users").document(uid).get().await()
        val user = snap.toObject(FirebaseUser::class.java) ?: FirebaseUser(uid, "", email, "student")
        Result.success(user)
    } catch (t: Throwable) {
        Result.failure(t)
    }

    suspend fun fetchSchedules(): Result<List<FirebaseSchedule>> = try {
        val res = db.collection("schedules").get().await().toObjects(FirebaseSchedule::class.java)
        Result.success(res)
    } catch (t: Throwable) {
        Result.failure(t)
    }

    suspend fun createSchedule(s: FirebaseSchedule): Result<Unit> = try {
        val id = if (s.id.isBlank()) db.collection("schedules").document().id else s.id
        db.collection("schedules").document(id).set(s.copy(id = id)).await()
        Result.success(Unit)
    } catch (t: Throwable) {
        Result.failure(t)
    }

    suspend fun markAttendance(a: FirebaseAttendance): Result<Unit> = try {
        val id = if (a.id.isBlank()) db.collection("attendance").document().id else a.id
        db.collection("attendance").document(id).set(a.copy(id = id)).await()
        Result.success(Unit)
    } catch (t: Throwable) {
        Result.failure(t)
    }

    suspend fun fetchAttendanceBySchedule(scheduleId: String): Result<List<FirebaseAttendance>> = try {
        val res = db.collection("attendance").whereEqualTo("scheduleId", scheduleId)
            .get().await().toObjects(FirebaseAttendance::class.java)
        Result.success(res)
    } catch (t: Throwable) {
        Result.failure(t)
    }
}
