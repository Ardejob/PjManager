package com.ardejo.pjmanager.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ardejo.pjmanager.R
import com.ardejo.pjmanager.databinding.FragmentRegisterBinding
import com.ardejo.pjmanager.utils.isValidEmail
import com.ardejo.pjmanager.viewmodels.RegisterViewModel
import com.ardejo.pjmanager.viewmodels.RegisterViewModel.RegistrationState.*

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val registrationViewModel by activityViewModels<RegisterViewModel>()
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false).apply {
            user = registrationViewModel.userLiveData
            password = registrationViewModel.passwordLiveData
        }

        navController = findNavController()

        binding.registerButton.setOnClickListener {
            if (isValidForm()) {
                registrationViewModel.createAccountAndLogin()
            }
        }

        binding.loginButton.setOnClickListener {
            cancelRegistration()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            cancelRegistration()
        }

        registrationViewModel.registrationState.observe(viewLifecycleOwner, Observer {
            when (it) {
                REGISTRATION_FAILED -> showErrorMessage()
                REGISTRATION_COMPLETED -> navController.popBackStack(R.id.home_fragment, false)
                else -> { }
            }
        })

        return binding.root
    }

    private fun showErrorMessage() {
        Toast.makeText(requireContext(), "Registration failed. Try again", Toast.LENGTH_LONG).show()
    }

    private fun cancelRegistration() {
        navController.popBackStack(R.id.login_fragment, false)
        registrationViewModel.userCancelledRegistration()
    }

    private fun isValidForm(): Boolean {
        var isValid = true

        if (binding.nameText.text.isNullOrBlank()) {
            binding.nameLayout.error = "Full name is required"
            isValid = false
        } else {
            binding.nameLayout.isErrorEnabled = false
        }

        if (binding.emailText.text.isNullOrBlank()) {
            binding.emailLayout.error = "Email is required"
            isValid = false
        } else if (!binding.emailText.text.toString().isValidEmail()) {
            binding.emailLayout.error = "Invalid email"
            isValid = false
        } else {
            binding.emailLayout.isErrorEnabled = false
        }

        if (binding.passwordText.text.isNullOrBlank()) {
            binding.passwordLayout.error = "Password is required"
            isValid = false
        } else {
            binding.passwordLayout.isErrorEnabled = false
        }

        return isValid
    }


}
