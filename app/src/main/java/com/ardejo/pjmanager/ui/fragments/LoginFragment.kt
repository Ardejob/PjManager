package com.ardejo.pjmanager.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ardejo.pjmanager.R
import com.ardejo.pjmanager.databinding.FragmentLoginBinding
import com.ardejo.pjmanager.utils.isValidEmail
import com.ardejo.pjmanager.viewmodels.LoginViewModel
import com.ardejo.pjmanager.viewmodels.LoginViewModel.AuthenticationState.*

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false).apply {
            viewModel = loginViewModel
        }

        val navController = findNavController()
        binding.loginButton.setOnClickListener {
            if (isValidForm()) {
                loginViewModel.authenticate()
            }
        }

        binding.registerButton.setOnClickListener {
            navController.navigate(R.id.action_login_fragment_to_register_fragment)
        }

        loginViewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            when (it) {
                AUTHENTICATED -> navController.popBackStack()
                INVALID_AUTHENTICATION -> showErrorMessage()
                else -> {
                    Log.d("LoginFragment", "Needs to login")
                }
            }
        })

        return binding.root
    }

    private fun showErrorMessage() {
        Toast.makeText(requireContext(), "Email or password is incorrect", Toast.LENGTH_LONG).show()
    }

    private fun isValidForm(): Boolean {
        var isValid = true

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
