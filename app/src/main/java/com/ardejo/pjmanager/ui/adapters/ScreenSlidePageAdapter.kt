package com.ardejo.pjmanager.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ardejo.pjmanager.R
import com.ardejo.pjmanager.models.Slide
import com.ardejo.pjmanager.ui.fragments.SliderScreenFragment

class ScreenSlidePageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val slides: List<Slide> = listOf(
            Slide(R.string.slide1_txt1, R.string.slide1_txt2, R.drawable.slide1_image),
            Slide(R.string.slide2_txt1, R.string.slide2_txt2, R.drawable.slide2_image),
            Slide(R.string.slide3_txt1, R.string.slide3_txt2, R.drawable.slide3_image),
            Slide(R.string.slide4_txt1, R.string.slide4_txt2, R.drawable.slide4_image)
    )

    override fun getItemCount(): Int = slides.count()

    override fun createFragment(position: Int): Fragment {
        val fragment = SliderScreenFragment()
        fragment.arguments = Bundle().apply {
            putParcelable(SliderScreenFragment.ARG_OBJECT, slides[position])
        }

        return fragment
    }

}