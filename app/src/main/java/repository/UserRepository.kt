package com.example.edututor.data.repository

import androidx.room.withTransaction
import com.example.edututor.data.local.AppDatabase
import com.example.edututor.data.local.entities.UserEntity
import com.example.edututor.data.remote.FirebaseDataSource
import com.example.edututor.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val db: AppDatabase,
    private val remote: FirebaseDataSource
) {

    fun observeUser(id: String): Flow<UserEntity> = db.userDao().observeUser(id) as Flow<UserEntity>

    fun register(name: String, email: String, password: String, role: String) = flow<Resource<UserEntity>> {
        emit(Resource.Loading()) // ← Fixed

        val res = remote.register(name, email, password, role)
        res.fold(
            onSuccess = { fu ->
                val entity = UserEntity(fu.id, fu.name, fu.email, fu.role)
                db.withTransaction { db.userDao().upsert(entity) } // suspending
                emit(Resource.Success(entity))
            },
            onFailure = { t ->
                emit(Resource.Error(t.message ?: "Registration failed"))
            }
        )

    }.catch { e -> emit(Resource.Error(e.message ?: "Unknown error")) }

    fun login(email: String, password: String) = flow<Resource<UserEntity>> {
        emit(Resource.Loading()) // ← Fixed

        val res = remote.login(email, password)
        res.fold(
            onSuccess = { fu ->
                val entity = UserEntity(fu.id, fu.name, fu.email, fu.role)
                db.withTransaction { db.userDao().upsert(entity) }
                emit(Resource.Success(entity))
            },
            onFailure = { t ->
                emit(Resource.Error(t.message ?: "Login failed"))
            }
        )

    }.catch { e -> emit(Resource.Error(e.message ?: "Unknown error")) }
}
