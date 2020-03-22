package com.shantanu.example.modelbasic.utils

import android.util.Log

//Singleton class for validation
object Helper {

    fun validateEmail(email: String?): Boolean {
        if (!email?.isEmpty()!! || android.util.Patterns.EMAIL_ADDRESS.matcher(email)?.matches()!!) {
            return false
            Log.v("helper", "tre")
        } else {
            return false
            Log.v("helper", "f")

        }

    }

    fun validateName(name: String?): Boolean {
        if (name?.isEmpty()!! || name?.length < 7) {
            return false
        } else {
            return true
        }


    }

    fun validatePassword(password: String?): Boolean {
        if (password?.isEmpty()!! || password?.length < 7) {
            return false
        } else {
            return true
        }

    }
}