package com.example.edututor.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    // Welcome message
    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to EduTutor Dashboard!"
    }
    val text: LiveData<String> = _text

    // LiveData for summary data
    private val _classCount = MutableLiveData<Int>().apply { value = 5 }   // Example: 5 scheduled classes
    val classCount: LiveData<Int> = _classCount

    private val _attendanceRate = MutableLiveData<Double>().apply { value = 85.0 } // Example: 85% attendance
    val attendanceRate: LiveData<Double> = _attendanceRate

    private val _pendingPayments = MutableLiveData<Int>().apply { value = 2 } // Example: 2 pending payments
    val pendingPayments: LiveData<Int> = _pendingPayments

    // Example functions to simulate updates (later replace with Firebase/DB calls)
    fun updateClassCount(count: Int) {
        _classCount.value = count
    }

    fun updateAttendanceRate(rate: Double) {
        _attendanceRate.value = rate
    }

    fun updatePendingPayments(count: Int) {
        _pendingPayments.value = count
    }

    fun updateWelcomeMessage(name: String) {
        _text.value = "Welcome back, $name 👋"
    }
}
