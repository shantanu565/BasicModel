package com.shantanu.example.modelbasic.ui.feature.forget

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.databinding.FragmentForgetPasswordBinding
import com.shantanu.example.modelbasic.databinding.FragmentRegisterBinding
import com.shantanu.example.modelbasic.ui.viewmodel.FirebaseBaseViewModel
import com.shantanu.example.modelbasic.utils.Helper

class ForgetPasswordFragment : Fragment() {
    private lateinit var model: FirebaseBaseViewModel
    private lateinit var binding: FragmentForgetPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_forget_password, container, false
        )
        model = ViewModelProviders.of(this)[FirebaseBaseViewModel::class.java]

        binding.lifecycleOwner = activity
        binding.forgetModel = model

        firebaseAuth = FirebaseAuth.getInstance()

        binding.fragmentForgetPasswordButtonRegister.setOnClickListener {
            //userForgetPasword()
            var email = binding.fragmentForgetPasswordEdittextEmail.text.toString()
            var check = Helper.validateEmail(email)
            Log.v("helper frag", check.toString())

            if (check) {
                //model.userForgetPasword(email)
                model.forgetUser(email)


            } else {
                binding.fragmentForgetPasswordEdittextEmail.requestFocus()
                binding.fragmentForgetPasswordEdittextEmail.text.clear()
                binding.fragmentForgetPasswordEdittextEmail.setError(
                    "Please enter a valid email",
                    resources.getDrawable(R.drawable.ic_arrow_back)
                )
            }


        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.fragmentForgetPasswordTextviewBackToLogin.setOnClickListener {
            findNavController().navigate(R.id.fragment_login)
        }

    }

    /* private fun userForgetPasword(){

         var email=binding.fragmentForgetPasswordEdittextEmail.text.toString()
         if (!TextUtils.isEmpty(email)) {
             firebaseAuth!!
                 .sendPasswordResetEmail(email)
                 .addOnCompleteListener { task ->
                     if (task.isSuccessful) {
                         val message = "Email sent."
 //                        Log.d(TAG, message)
                         Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                         updateUI()
                     } else {
   //                      Log.w(TAG, task.exception!!.message)
                         Toast.makeText(activity, "No user found with this email.", Toast.LENGTH_SHORT).show()
                     }
                 }
         } else {
             Toast.makeText(activity, "Enter Email", Toast.LENGTH_SHORT).show()
         }
     }
     }*/

    private fun updateUI() {

    }
}