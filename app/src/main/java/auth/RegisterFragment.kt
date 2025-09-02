package com.example.edututor.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.edututor.R
import com.example.edututor.databinding.FragmentRegisterBinding
import com.example.edututor.ui.shared.BaseFragment
import com.example.edututor.utils.Resource
import com.example.edututor.utils.toast

class RegisterFragment : BaseFragment(R.layout.fragment_register) {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val vm: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etName.text.toString() // Make sure you have this EditText
            val role = if (binding.rbTutor.isChecked) "tutor" else "student"

            vm.register(name, email, password, confirmPassword, role)
        }

        vm.registerState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Loading<*> -> binding.progress.visibility = View.VISIBLE
                is Resource.Success<*> -> {
                    binding.progress.visibility = View.GONE
                    toast("Registered! Please login.")
                    findNavController().navigateUp()
                }
                is Resource.Error<*> -> {
                    binding.progress.visibility = View.GONE
                    toast(state.message ?: "Registration failed. Please try again.")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
