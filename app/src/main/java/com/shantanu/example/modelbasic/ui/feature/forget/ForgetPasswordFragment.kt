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
import androidx.lifecycle.Observer
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
    private var success: Boolean = false


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



        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //email validation for password reset
        binding.fragmentForgetPasswordButtonRegister.setOnClickListener {
            updatePassword()
        }
    }

    //calling firebase method to reset password
    private fun updatePassword() {
        var email = binding.fragmentForgetPasswordEdittextEmail.text.toString()
        var check = Helper.validateEmail(email)

        if (check) {
            model.userForgetPasword(email).observe(this, Observer {
                success = it
                if (success) {
                    Toast.makeText(
                        activity,
                        "Change Successful. Please check your mail",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(activity, "Email not found", Toast.LENGTH_SHORT).show()

                }
            })


        } else {
            binding.fragmentForgetPasswordEdittextEmail.requestFocus()
            binding.fragmentForgetPasswordEdittextEmail.text.clear()
            binding.fragmentForgetPasswordEdittextEmail.setError(
                "Please enter a valid email",
                resources.getDrawable(R.drawable.ic_arrow_back)
            )
        }

    }


}