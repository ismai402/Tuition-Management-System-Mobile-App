package com.example.edututor.ui.student

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.example.edututor.data.local.entities.AttendanceEntity
import com.example.edututor.di.AppModule
import com.example.edututor.model.Performance
import com.example.edututor.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {

    private val attRepo = AppModule.attendance()

    // Combine flows to get performance as LiveData
    val performance = attRepo.totalByStudent("me-student").combine(
        attRepo.presentByStudent("me-student")
    ) { total, present ->
        Performance("me-student", total, present)
    }.asLiveData()

    // Mutable LiveData for marking attendance
    private val _markState = MutableLiveData<Resource<Unit>>()
    val markState: LiveData<Resource<Unit>> = _markState

    // Mark attendance function
    fun mark(a: AttendanceEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            _markState.postValue(Resource.Loading())
            val res = attRepo.mark(a)
            _markState.postValue(res)
        }
    }
}
