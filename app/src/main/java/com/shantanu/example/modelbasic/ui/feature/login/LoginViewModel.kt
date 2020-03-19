package com.shantanu.example.modelbasic.ui.feature.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shantanu.example.modelbasic.network.data.local.User

class LoginViewModel : ViewModel() {
    var emailAddress = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private var userMutableLiveData: MutableLiveData<User>? = null

    fun getUser(): MutableLiveData<User> {

        if (userMutableLiveData == null) {
            userMutableLiveData = MutableLiveData<User>()
        }
        return userMutableLiveData as MutableLiveData<User>

    }

    fun saveUser(email: String, password: String) {

        val loginUser = User(email,password)

        userMutableLiveData?.setValue(loginUser)
        // Log.v("check ","value")

    }

    fun validation(email: String, password: String){

    }

}