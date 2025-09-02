package com.example.edututor.ui.student

import android.os.Bundle
import android.view.View
import com.example.edututor.R
import com.example.edututor.data.local.entities.AttendanceEntity
import com.example.edututor.databinding.FragmentMarkAttendanceBinding
import com.example.edututor.ui.shared.BaseFragment
import com.example.edututor.utils.DateUtils
import com.example.edututor.utils.Resource
import com.example.edututor.utils.toast
import androidx.fragment.app.viewModels

class MarkAttendanceFragment : BaseFragment(R.layout.fragment_mark_attendance) {
    private var _binding: FragmentMarkAttendanceBinding? = null
    private val binding get() = _binding!!
    private val vm: StudentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMarkAttendanceBinding.bind(view)

        binding.btnPresent.setOnClickListener {
            vm.mark(AttendanceEntity("", "sample-schedule", "me-student", "present", DateUtils.nowString()))
        }
        binding.btnAbsent.setOnClickListener {
            vm.mark(AttendanceEntity("", "sample-schedule", "me-student", "absent", DateUtils.nowString()))
        }

        vm.markState.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> binding.progress.visibility = View.VISIBLE
                is Resource.Success -> { binding.progress.visibility = View.GONE; toast("Marked") }
                is Resource.Error -> { binding.progress.visibility = View.GONE; toast(it.message) }
            }
        }
    }
}
