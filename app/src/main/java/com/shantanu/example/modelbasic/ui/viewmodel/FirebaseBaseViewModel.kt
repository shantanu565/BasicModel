package com.shantanu.example.modelbasic.ui.viewmodel

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.shantanu.example.modelbasic.network.data.local.User

//Common ViewModel for application consisting of firebase methods
class FirebaseBaseViewModel : ViewModel() {
    private var userResponse = MutableLiveData<User>()
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    var query = MutableLiveData<String>()

    //firebase registration
    fun registerUser(email: String, password: String) {
        firebaseAuth!!
            .createUserWithEmailAndPassword(email!!, password!!)

            .addOnCompleteListener { task ->
                // mProgressBar!!.hide()
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val userId = firebaseAuth!!.currentUser!!.uid
                    Log.v("uid", userId)


                    // verifyEmail();

                    updateUserInfoAndUI()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    // Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateUserInfoAndUI() {
        val mUser = firebaseAuth!!.currentUser;
        mUser!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                } else {
                    Log.e("TAG", "sendEmailVerification", task.exception)
                    //Toast.makeText(activity, "Failed to send verification email.", Toast.LENGTH_SHORT).show()
                }
            }


    }

    //firebase login
    fun userLogin(email: String, password: String) {

        firebaseAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                //mProgressBar!!.hide()
                if (task.isSuccessful) {
                    // Sign in success, update UI with signed-in user's information
                    // Log.d(TAG, "signInWithEmail:success")
                    Log.v("login", "login")
                    //updateUI()
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.e(TAG, "signInWithEmail:failure", task.exception)
                    //Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }

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
    fun getUser(user: User): LiveData<User> {
        getSearchFilters()
        return userResponse
    }

    fun forgetUser(email: String): LiveData<User> {
        userForgetPasword(email)
        return userResponse
    }


    //firebase logout
    fun logout() {
        firebaseAuth.signOut()
    }


    //firbase get current user id
    fun currentUser() {
        firebaseAuth.currentUser
    }
}
