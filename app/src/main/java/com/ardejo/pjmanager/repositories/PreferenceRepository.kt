package com.ardejo.pjmanager.repositories

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PreferenceRepository(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(DEFAULT_PREFERENCES, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    var nightMode: Int = PREFERENCE_NIGHT_MODE_DEF_VAL
        get() = sharedPreferences.getInt(PREFERENCE_NIGHT_MODE, PREFERENCE_NIGHT_MODE_DEF_VAL)
        set(value) {
            editor.putInt(PREFERENCE_NIGHT_MODE, value).apply()
            field = value
        }

    var firstTimeLaunch: Boolean = PREFERENCE_FIRST_TIME_LAUNCH_DEF_VALUE
        get() = sharedPreferences.getBoolean(PREFERENCE_FIRST_TIME_LAUNCH, PREFERENCE_FIRST_TIME_LAUNCH_DEF_VALUE)
        set(value) {
            editor.putBoolean(PREFERENCE_FIRST_TIME_LAUNCH, value).apply()
            field = value
        }

    private val _nightModeLive: MutableLiveData<Int> = MutableLiveData()
    val nightModeLive: LiveData<Int>
        get() = _nightModeLive

    private val preferenceChangeListener =
            SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
                when(key) {
                    PREFERENCE_NIGHT_MODE -> {
                        _nightModeLive.value = nightMode
                    }
                }
            }

    init {
        _nightModeLive.value = nightMode

        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    companion object {
        private const val DEFAULT_PREFERENCES = "default_preferences"

        private const val PREFERENCE_NIGHT_MODE = "preference_night_mode"
        private const val PREFERENCE_NIGHT_MODE_DEF_VAL = AppCompatDelegate.MODE_NIGHT_NO

        private const val PREFERENCE_FIRST_TIME_LAUNCH = "preference_first_time_launch"
        private const val PREFERENCE_FIRST_TIME_LAUNCH_DEF_VALUE = true
    }
}