package com.ardejo.pjmanager.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ardejo.pjmanager.App
import com.ardejo.pjmanager.R
import com.ardejo.pjmanager.databinding.FragmentWelcomeBinding
import com.ardejo.pjmanager.ui.adapters.ScreenSlidePageAdapter
import com.ardejo.pjmanager.ui.adapters.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayoutMediator

class WelcomeFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        navController = findNavController()

        val adapter = ScreenSlidePageAdapter(this)
        binding.viewPager.adapter = adapter
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {_, _ -> }.attach()

        val repository = (requireActivity().application as App).preferenceRepository

        binding.nextButton.setOnClickListener {
            if (binding.viewPager.currentItem == adapter.itemCount - 1) {
                repository.firstTimeLaunch = true
                navController.navigate(R.id.home_fragment)
            } else {
                binding.viewPager.currentItem = binding.viewPager.currentItem + 1
            }
        }

        binding.skipButton.setOnClickListener {
            repository.firstTimeLaunch = true
            navController.navigate(R.id.home_fragment)
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (binding.viewPager.currentItem > 0) {
                binding.viewPager.currentItem = binding.viewPager.currentItem - 1
            }
        }

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                callback.isEnabled = position > 0

                if (position == adapter.itemCount - 1) {
                    binding.nextButton.text = getString(R.string.start)
                    binding.skipButton.visibility = View.INVISIBLE
                } else {
                    binding.nextButton.text = getText(R.string.next)
                    binding.skipButton.visibility = View.VISIBLE
                }
            }
        })

        return binding.root
    }



}
