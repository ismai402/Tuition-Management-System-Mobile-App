package com.example.edututor.ui.tutor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.edututor.R
import com.example.edututor.data.local.entities.ScheduleEntity
import com.example.edututor.databinding.FragmentCreateScheduleBinding
import com.example.edututor.ui.shared.BaseFragment
import com.example.edututor.utils.DateUtils
import com.example.edututor.utils.Resource
import com.example.edututor.utils.toast

class CreateScheduleFragment : BaseFragment(R.layout.fragment_create_schedule) {

    private var _binding: FragmentCreateScheduleBinding? = null
    private val binding get() = _binding!!
    private val vm: TutorViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateScheduleBinding.bind(view)

        binding.btnRefreshSchedules.setOnClickListener {
            val schedule = ScheduleEntity(
                id = "", // let backend assign ID
                title = binding.etTitle.text.toString(),
                subject = binding.etSubject.text.toString(),
                dateTime = DateUtils.nowString(),
                tutorId = "me" // replace with actual tutor ID
            )
            vm.create(schedule)
        }

        vm.createState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Loading<*> -> binding.progress.visibility = View.VISIBLE
                is Resource.Success<*> -> {
                    binding.progress.visibility = View.GONE
                    toast("Created")
                }
                is Resource.Error<*> -> {
                    binding.progress.visibility = View.GONE
                    toast(state.message ?: "Failed to create schedule")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
