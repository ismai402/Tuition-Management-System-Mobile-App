package com.example.edututor.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.edututor.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        // Example: button clicks
        binding.btnSchedule.setOnClickListener {
            Toast.makeText(requireContext(), "Go to Class Schedule", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to ScheduleFragment
        }

        binding.btnAttendance.setOnClickListener {
            Toast.makeText(requireContext(), "Go to Attendance", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to AttendanceFragment
        }

        binding.btnPayment.setOnClickListener {
            Toast.makeText(requireContext(), "Go to Payments", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to PaymentFragment
        }

        binding.btnPerformance.setOnClickListener {
            Toast.makeText(requireContext(), "Go to Performance", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to PerformanceFragment
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
