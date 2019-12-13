package com.ardejo.pjmanager.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ardejo.pjmanager.databinding.FragmentSliderScreenBinding

class SliderScreenFragment : Fragment() {

    private lateinit var binding: FragmentSliderScreenBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSliderScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            binding.slide = getParcelable(ARG_OBJECT)
        }
    }

    companion object {
        const val ARG_OBJECT = "object"
    }

}