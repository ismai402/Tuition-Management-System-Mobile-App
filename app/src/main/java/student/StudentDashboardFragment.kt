package com.example.edututor.ui.student

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.edututor.R
import com.example.edututor.databinding.FragmentStudentDashboardBinding
import com.example.edututor.ui.shared.BaseFragment

class StudentDashboardFragment : BaseFragment(R.layout.fragment_student_dashboard) {
    private var _binding: FragmentStudentDashboardBinding? = null
    private val binding get() = _binding!!
    private val vm: StudentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStudentDashboardBinding.bind(view)

        vm.performance.observe(viewLifecycleOwner) { p ->
            binding.tvSummary.text = "Attendance: %.1f%%".format(p.attendanceRate)
        }
    }
}
