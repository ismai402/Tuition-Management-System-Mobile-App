package com.example.edututor.ui.student

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.edututor.R
import com.example.edututor.databinding.FragmentViewPerformanceBinding
import com.example.edututor.ui.shared.BaseFragment

class ViewPerformanceFragment : BaseFragment(R.layout.fragment_view_performance) {
    private var _binding: FragmentViewPerformanceBinding? = null
    private val binding get() = _binding!!
    private val vm: StudentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentViewPerformanceBinding.bind(view)

        vm.performance.observe(viewLifecycleOwner) { p ->
            binding.tvPerformance.text = "Total: ${p.totalClasses}, Present: ${p.presentCount}, Rate: %.1f%%".format(p.attendanceRate)
        }
    }
}
