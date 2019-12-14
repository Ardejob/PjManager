package com.ardejo.pjmanager.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ardejo.pjmanager.R
import com.ardejo.pjmanager.databinding.FragmentHomeBinding
import com.ardejo.pjmanager.viewmodels.LoginViewModel
import com.ardejo.pjmanager.viewmodels.LoginViewModel.AuthenticationState.*

class HomeFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        loginViewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                UNAUTHENTICATED -> findNavController().navigate(R.id.login_fragment)
                else -> {
                    Log.d("HomeFragment", "Logged in")
                }
            }
        })

        return binding.root
    }


}
