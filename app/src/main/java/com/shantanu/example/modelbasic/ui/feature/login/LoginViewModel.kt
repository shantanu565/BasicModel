package com.shantanu.example.modelbasic.ui.feature.login

import android.content.Context
import android.preference.PreferenceManager
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.network.data.local.User
import com.shantanu.example.modelbasic.network.firebase.FirebaseUserLiveData
import java.util.regex.Pattern
import kotlin.random.Random

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

        val loginUser = User(email, password)

        userMutableLiveData?.setValue(loginUser)


        // Log.v("check ","value")

    }

    fun validation(email: String, password: String) {
        if (email.isEmpty() && password.isEmpty()) {

        } else if (email.length < 12 && password.length < 5) {

        } else {

        }

    }

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    /* val authenticationState = FirebaseUserLiveData().map { user ->
         if (user != null) {
             AuthenticationState.AUTHENTICATED
         } else {
             AuthenticationState.UNAUTHENTICATED
         }
     }*/

    /**
     * Gets a fact to display based on the user's set preference of which type of fact they want
     * to see (Android fact or California fact). If there is no logged in user or if the user has
     * not set a preference, defaults to showing Android facts.
     */


}