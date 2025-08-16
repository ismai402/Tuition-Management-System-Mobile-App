package com.example.edututor.ui.home

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.edututor.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set User info
        binding.textUserName.text = "Ismail Hossain"
        binding.textUserRole.text = "Student"

        // Notifications click
        binding.imageNotifications.setOnClickListener {
            Toast.makeText(requireContext(), "Notifications clicked", Toast.LENGTH_SHORT).show()
        }

        // Subject names with colors (horizontal list)
        val subjects = listOf(
            "Physics" to "#C5E1A5",
            "Chemistry" to "#FFCDD2",
            "Math" to "#BBDEFB",
            "English" to "#FFF9C4",
            "Bangla" to "#D1C4E9"
        )
        subjects.forEach { (subject, color) ->
            val textView = AppCompatTextView(requireContext())
            textView.text = subject
            textView.setPadding(24, 12, 24, 12)
            textView.setBackgroundColor(Color.parseColor(color))
            textView.setTextColor(Color.BLACK)

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            textView.layoutParams = params

            binding.subjectList.addView(textView)
        }

        // Filter Spinners
        val classOptions = listOf("All Classes", "Class 6", "Class 7", "Class 8", "Class 9")
        val subjectOptions = listOf("All Subjects", "Physics", "Chemistry", "Math", "English", "Bangla")
        val onlineOfflineOptions = listOf("All", "Online", "Offline")

        binding.spinnerClassFilter.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, classOptions)
        binding.spinnerOnlineOffline.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, onlineOfflineOptions)
        binding.spinnerSubjectFilter.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, subjectOptions)

        // Multi-select Chips for subjects
        val chipSubjects = listOf("All Subjects", "Physics", "Chemistry", "Math", "English", "Bangla")
        chipSubjects.forEach { subject ->
            val chip = Chip(requireContext())
            chip.text = subject
            chip.isCheckable = true
            chip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor("#C5E1A5"))
            chip.setTextColor(Color.BLACK)

            chip.setOnCheckedChangeListener { _, isChecked ->
                if (subject == "All Subjects" && isChecked) {
                    // Uncheck other chips
                    for (i in 1 until binding.chipGroupSubjects.childCount) {
                        val otherChip = binding.chipGroupSubjects.getChildAt(i) as Chip
                        otherChip.isChecked = false
                    }
                } else if (subject != "All Subjects" && isChecked) {
                    // Uncheck "All Subjects"
                    val allChip = binding.chipGroupSubjects.getChildAt(0) as Chip
                    allChip.isChecked = false
                }
            }
            binding.chipGroupSubjects.addView(chip)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
