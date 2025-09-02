package com.example.edututor.ui.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.edututor.di.AppModule

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!::initialized.isInitialized) {
            // nothing
        }
    }
    // quick accessors
    protected val users get() = AppModule.users()
    protected val schedules get() = AppModule.schedules()
    protected val attendance get() = AppModule.attendance()
    private lateinit var initialized: String
}
