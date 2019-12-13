package com.ardejo.pjmanager.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.ardejo.pjmanager.App
import com.ardejo.pjmanager.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).preferenceRepository.nightModeLive.observe(this, Observer { nightMode ->
            nightMode?.let {
                delegate.localNightMode = it
            }
        })
    }

    override fun onNavigateUp(): Boolean {
        return findNavController(R.id.nav_container).navigateUp() || super.onNavigateUp()
    }
}