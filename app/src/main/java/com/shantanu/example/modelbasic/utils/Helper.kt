package com.shantanu.example.modelbasic.utils

import android.util.Log

//Singleton class for validation
object Helper {

    fun validateEmail(email: String?): Boolean {
        if (email?.length!!<10){
            return false
        }

        if (email?.isEmpty()){
            return false
        }

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email)?.matches()!!) {

            Log.v("helper", "tre")
            return true

        }else{
            return false
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