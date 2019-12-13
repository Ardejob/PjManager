package com.ardejo.pjmanager

import android.app.Application
import com.ardejo.pjmanager.repositories.PreferenceRepository

class App : Application() {

    lateinit var preferenceRepository: PreferenceRepository

    override fun onCreate() {
        super.onCreate()
        preferenceRepository = PreferenceRepository(this)
    }
}