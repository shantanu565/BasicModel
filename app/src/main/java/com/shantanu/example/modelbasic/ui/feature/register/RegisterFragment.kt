package com.shantanu.example.modelbasic.ui.feature.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.databinding.FragmentRegisterBinding
import com.shantanu.example.modelbasic.network.data.local.User
import com.shantanu.example.modelbasic.ui.feature.home.HomeActivity
import com.shantanu.example.modelbasic.ui.feature.login.LoginViewModel
import com.shantanu.example.modelbasic.ui.viewmodel.FirebaseBaseViewModel
import com.shantanu.example.modelbasic.utils.Helper

class RegisterFragment : Fragment() {
    private lateinit var model: FirebaseBaseViewModel
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userData: MutableLiveData<User>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false
        )
        model = ViewModelProviders.of(this)[FirebaseBaseViewModel::class.java]

        binding.lifecycleOwner = activity
        binding.registerViewModel = model

        firebaseAuth = FirebaseAuth.getInstance()

        binding.fragmentRegisterationTextviewBackToLogin.setOnClickListener {
            //findNavController().navigate(R.id.action_fragment_registeration_to_fragment_login)
        }

        binding.fragmentRegisterationButtonRegister.setOnClickListener {
            var name: String? = binding.fragmentRegisterationEdittextUsername.text.toString()
            var pass = binding.fragmentRegisterationEdittextPassword.text.toString()
            var email = binding.fragmentRegisterationEdittextEmail.text.toString()

            Helper.validateEmail(email)
            Helper.validateName(name)
            Helper.validatePassword(pass)

            model.registerUser(email, pass)


        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    /* private fun registerUser(){
         var name=binding.fragmentRegisterationEdittextUsername.text.toString()
         var pass=binding.fragmentRegisterationEdittextPassword.text.toString()
         var email=binding.fragmentRegisterationEdittextEmail.text.toString()

         firebaseAuth!!
             .createUserWithEmailAndPassword(email!!, pass!!)
             .addOnCompleteListener(activity!!) { task ->
                // mProgressBar!!.hide()
                 if (task.isSuccessful) {
                     // Sign in success, update UI with the signed-in user's information
                     Log.d("TAG", "createUserWithEmail:success")
                     val userId = firebaseAuth!!.currentUser!!.uid
                     Log.v("uid",userId)


                     verifyEmail();

                    // updateUserInfoAndUI()
                 } else {
                     // If sign in fails, display a message to the user.
                     Log.w("TAG", "createUserWithEmail:failure", task.exception)
                     Toast.makeText(activity, "Authentication failed.",
                         Toast.LENGTH_SHORT).show()
                 }
             }
     }
 */
    private fun verifyEmail() {
        findNavController().navigate(R.id.fragment_home)
    }


}