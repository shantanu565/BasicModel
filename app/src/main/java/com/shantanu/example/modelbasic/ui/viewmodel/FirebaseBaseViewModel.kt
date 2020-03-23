package com.shantanu.example.modelbasic.ui.viewmodel

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.shantanu.example.modelbasic.network.data.local.User

//Common ViewModel for application consisting of firebase methods
class FirebaseBaseViewModel : ViewModel() {
   // private var userResponse = MutableLiveData<User>()
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    var email = MutableLiveData<String>()
    var successRegistration=MutableLiveData<Boolean>()
    var successLogin=MutableLiveData<Boolean>()
    //var successLogin=MutableLiveData<Boolean>()



    //firebase registration
    fun registerUser(email: String, password: String):LiveData<Boolean> {
        firebaseAuth!!
            .createUserWithEmailAndPassword(email!!, password!!)

            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    Log.d("TAG", "createUserWithEmail:success")
                    val userId = firebaseAuth!!.currentUser!!.uid
                    Log.v("uid", userId)
                    successRegistration.value=true

                    updateUserInfoAndUI()
                } else {
                    successRegistration.value=false
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                }
            }
        return successRegistration
    }

    private fun updateUserInfoAndUI() {
        val mUser = firebaseAuth!!.currentUser;
        mUser!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                } else {
                    Log.e("TAG", "sendEmailVerification", task.exception)
                }
            }


    }

    //firebase login
    fun userLogin(email: String, password: String) :LiveData<Boolean>{

        firebaseAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                //mProgressBar!!.hide()
                if (task.isSuccessful) {
                    successLogin.value=true
                    Log.v("login", "login")
                    val userId = firebaseAuth!!.currentUser!!.uid
                    //updateUI()
                } else {
                    successLogin.value=false
                    Log.v("login", "login not")

                    Log.e("error", "signInWithEmail:failure", task.exception)
                    //Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
        return successLogin

    }

    //firebase forget password
    fun userForgetPasword(email: String) {

        if (!TextUtils.isEmpty(email)) {
            firebaseAuth!!
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.v("vm", "vm")
                        val message = "Email sent."
//                        Log.d(TAG, message)
                        //Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                        // updateUI()
                    } else {
                        //                      Log.w(TAG, task.exception!!.message)
                        //Toast.makeText(activity, "No user found with this email.", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            //Toast.makeText(activity, "Enter Email", Toast.LENGTH_SHORT).show()
        }


    }


    fun getSearchFilters() {
        //userResponse.value()
    }

    //Method for geeting the query
    /*fun getUser(user: User): LiveData<User> {
        getSearchFilters()
        return userResponse
    }

    fun forgetUser(email: String): LiveData<User> {
        userForgetPasword(email)
        return userResponse
    }*/


    //firebase logout
    fun logout() {
        firebaseAuth.signOut()
    }


    //firbase get current user id
    fun currentUser(): FirebaseUser? {
        var session=firebaseAuth.currentUser
        return session
    }

    fun getDetail():String{
        val detail="Firebase is a mobile and web application development platform developed by Firebase, Inc. in 2011, then acquired by Google in 2014. As of March 2020, the Firebase platform has 19 products, which are used more than 1.5 million apps including 9GAG."
        return detail
    }

    fun getUserInfo():LiveData<String>{
        email.value=firebaseAuth.currentUser?.email
        return email
    }
}
