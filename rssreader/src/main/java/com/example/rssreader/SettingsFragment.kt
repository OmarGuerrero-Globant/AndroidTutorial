package com.example.rssreader

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.Preference
import android.preference.PreferenceCategory
import android.preference.PreferenceFragment


class SettingsFragment : PreferenceFragment(), OnSharedPreferenceChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.mypreferences)
        (0 until preferenceScreen.preferenceCount).forEach { i ->
            initSummary(preferenceScreen.getPreference(i))
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences
            .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences
            .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(
        sharedPreferences: SharedPreferences,
        key: String
    ) {
        updatePreferences(findPreference(key))
    }

    private fun initSummary(p: Preference) {
        if (p is PreferenceCategory) {
            (0 until p.preferenceCount).forEach { i ->
                initSummary(p.getPreference(i))
            }
        } else {
            updatePreferences(p)
        }
    }

    private fun updatePreferences(p: Preference) {
        if (p is EditTextPreference) p.setSummary(p.text)
    }
}