package com.example.edututor.ui.tutor

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.edututor.R
import com.example.edututor.databinding.FragmentViewAttendanceBinding
import com.example.edututor.ui.shared.BaseFragment
import com.example.edututor.utils.Resource

class ViewAttendanceFragment : BaseFragment(R.layout.fragment_view_attendance) {
    private var _binding: FragmentViewAttendanceBinding? = null
    private val binding get() = _binding!!
    private val vm: TutorViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentViewAttendanceBinding.bind(view)

        vm.attendance.observe(viewLifecycleOwner) { list ->
            val rows = list.map { it.studentId + " - " + it.status + " @ " + it.timestamp }
            binding.lvAttendance.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, rows)
        }

        vm.refreshAttendance("sample-schedule")
        vm.attendanceState.observe(viewLifecycleOwner) {
            binding.progress.visibility = if (it is Resource.Loading) View.VISIBLE else View.GONE
        }
    }
}
