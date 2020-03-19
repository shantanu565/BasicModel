package com.shantanu.example.modelbasic.network.data.local

import android.content.Context
import android.content.SharedPreferences

class AppPreference(private val context: Context) {
    private val PREFS_NAME = "test_pref"
    private val PREFS_USERNAME = "username"
    private val PREFS_EMAIL = "email"
    private val PREFS_ISLOGGEDIN = "is_loggedin"
    private val app_prefs: SharedPreferences

    init {
        app_prefs = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun putIsLogin(loginorout: Boolean):Boolean {
        val edit = app_prefs.edit()
        edit.putBoolean(PREFS_ISLOGGEDIN, loginorout)
        edit.commit()
        return loginorout
    }

    fun getIsLogin(): Boolean {
        var check:Boolean=app_prefs.getBoolean(PREFS_ISLOGGEDIN,false)
        return check
    }

    fun putName(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(PREFS_USERNAME, loginorout)
        edit.commit()
    }

    fun getNames(): String? {
        return app_prefs.getString(PREFS_USERNAME, "")
    }

    fun putEmail(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(PREFS_EMAIL, loginorout)
        edit.commit()
    }

    fun getEmail(): String? {
        return app_prefs.getString(PREFS_EMAIL, "")
    }

    fun logout() {

        val editor=app_prefs.edit()

        //sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        editor.clear()
        // editor.apply()
        editor.commit()
    }

}