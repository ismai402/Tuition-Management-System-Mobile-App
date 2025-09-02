package com.example.edututor.ui.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    private val handler = CoroutineExceptionHandler { _, _ -> }
    protected fun io(block: suspend CoroutineScope.() -> Unit): Job =
        viewModelScope.launch(Dispatchers.IO + handler) { block() }
}
