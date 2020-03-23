package com.shantanu.example.modelbasic.utils

import android.util.Log

//Singleton class for validation
object Helper {

    //for email validation
    fun validateEmail(email: String?): Boolean {
        if (email?.length!! < 10) {
            return false
        }

        if (email?.isEmpty()) {
            return false
        }

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email)?.matches()!!) {

            return true

        } else {
            return false
        }


    }

    //for username validation
    fun validateName(name: String?): Boolean {
        if (name?.isEmpty()!! || name?.length < 8) {
            return false
        } else {
            return true
        }


    }


    //for password validation
    fun validatePassword(password: String?): Boolean {
        if (password?.isEmpty()!! || password?.length < 8) {
            return false
        } else {
            return true
        }

    }
}