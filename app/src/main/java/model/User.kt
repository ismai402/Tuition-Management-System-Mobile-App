package com.example.edututor.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = "student" // or "tutor"
): Parcelable
