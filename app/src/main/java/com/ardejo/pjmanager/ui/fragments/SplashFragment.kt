package com.ardejo.pjmanager.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ardejo.pjmanager.App
import com.ardejo.pjmanager.R
import com.ardejo.pjmanager.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            delay(2500)

            val navController = findNavController()
            val repository = (requireActivity().application as App).preferenceRepository
            if (repository.firstTimeLaunch) {
                navController.navigate(R.id.action_splash_fragment_to_welcome_fragment)
                repository.firstTimeLaunch = false
            } else {
                navController.navigate(R.id.action_splash_fragment_to_home_fragment)
            }
        }
    }
}
