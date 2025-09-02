package com.example.edututor.ui.tutor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.edututor.data.local.entities.AttendanceEntity
import com.example.edututor.data.local.entities.ScheduleEntity
import com.example.edututor.di.AppModule
import com.example.edututor.utils.DateUtils
import com.example.edututor.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TutorViewModel : ViewModel() {
     val repo = AppModule.schedules()
    private val attRepo = AppModule.attendance()

    val schedules: LiveData<List<ScheduleEntity>> = repo.observeSchedules().asLiveData()

    private val _refreshState = MutableLiveData<Resource<List<ScheduleEntity>>>()
    val refreshState: LiveData<Resource<List<ScheduleEntity>>> = _refreshState

    private val _createState = MutableLiveData<Resource<Unit>>()
    val createState: LiveData<Resource<Unit>> = _createState

    private val _attendance = attRepo.observeBySchedule("sample-schedule").asLiveData()
    val attendance: LiveData<List<AttendanceEntity>> = _attendance

    private val _attendanceState = MutableLiveData<Resource<List<AttendanceEntity>>>()
    val attendanceState: LiveData<Resource<List<AttendanceEntity>>> = _attendanceState

    fun refresh() {
        CoroutineScope(Dispatchers.IO).launch {
            repo.refreshSchedules().collect { _refreshState.postValue(it) }
        }
    }

    fun create(s: ScheduleEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            _createState.postValue(Resource.Loading())
            val res = repo.createSchedule(s)
            _createState.postValue(res)
        }
    }

    fun createSampleSchedule() {
        create(ScheduleEntity("", "Sample Class", "Math", DateUtils.nowString(), "me"))
    }

    fun refreshAttendance(scheduleId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            attRepo.refreshBySchedule(scheduleId).collect { _attendanceState.postValue(it) }
        }
    }
}
