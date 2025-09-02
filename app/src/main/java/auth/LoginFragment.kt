package com.example.edututor.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.edututor.R
import com.example.edututor.databinding.FragmentLoginBinding
import com.example.edututor.ui.shared.BaseFragment
import com.example.edututor.utils.Resource
import com.example.edututor.utils.toast

class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val vm: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            vm.login(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }

        binding.tvGoRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        vm.loginState.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> binding.progress.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val user = it.data
                    if (user != null) {
                        val role = user.role ?: "student"  // default to "student" if null
                        if (role == "tutor") {
                            findNavController().navigate(R.id.action_login_to_tutorDashboard)
                        } else {
                            findNavController().navigate(R.id.action_login_to_studentDashboard)
                        }
                    } else {
                        toast("User data is missing")
                    }
                }

                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    toast(it.message)
                }
            }
        }
    }
}
