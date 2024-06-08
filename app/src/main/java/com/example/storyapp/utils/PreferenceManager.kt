package com.example.storyapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.storyapp.data.model.status.auth.LoginResult

class PreferenceManager(context: Context) {
    private var prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    private val editor = prefs.edit()

    fun setStringPreference(prefKey: String, value: String) {
        editor.putString(prefKey, value)
        editor.apply()
    }

    fun setLoginPref(user: LoginResult) {
        user.let {
            setStringPreference(TOKEN_KEY, it.token)
            setStringPreference(NAME_KEY, it.name)
        }
    }

    fun clearAllPreferences() {
        editor.remove(TOKEN_KEY)
        editor.remove(NAME_KEY)
        editor.apply()
    }

    val getToken = prefs.getString(TOKEN_KEY, "") ?: ""
    val name = prefs.getString(NAME_KEY, "") ?: ""
}