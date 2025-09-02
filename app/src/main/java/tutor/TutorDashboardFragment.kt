package com.example.edututor.ui.tutor

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.edututor.R
import com.example.edututor.data.local.entities.ScheduleEntity
import com.example.edututor.databinding.FragmentTutorDashboardBinding
import com.example.edututor.ui.shared.BaseFragment
import com.example.edututor.utils.Resource

class TutorDashboardFragment : BaseFragment(R.layout.fragment_tutor_dashboard) {

    private var _binding: FragmentTutorDashboardBinding? = null
    private val binding get() = _binding!!
    private val vm: TutorViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTutorDashboardBinding.bind(view)

        // Button clicks
        binding.btnCreateSchedule.setOnClickListener { vm.createSampleSchedule() }
        binding.btnRefreshSchedules.setOnClickListener { vm.refresh() }

        // Observe schedules
        vm.schedules.observe(viewLifecycleOwner) { list: List<ScheduleEntity> ->
            // Display title, subject, and datetime in the list
            val titles = list.map { "${it.title} (${it.subject}) - ${it.dateTime}" }

            binding.lvSchedules.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, titles)
        }

        // Observe refresh state
        // Observe refresh state
        vm.refreshState.observe(viewLifecycleOwner) { state ->
            binding.progress.visibility = if (state is Resource.Loading) View.VISIBLE else View.GONE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
