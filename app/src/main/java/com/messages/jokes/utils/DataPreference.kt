package com.messages.jokes.utils

import android.content.Context
import android.preference.PreferenceManager.getDefaultSharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataPreference @Inject constructor(@ApplicationContext context : Context){
    private val PREF_TAG: String = "Times"
    val prefs = getDefaultSharedPreferences(context)

    fun getStoredTag(): Int {
        return prefs.getInt(PREF_TAG, 0)
    }
    fun setStoredTag(time: Int) {
        prefs.edit().putInt(PREF_TAG, time).apply()
    }
}