package com.shantanu.example.modelbasic.ui.feature.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.databinding.FragmentRegisterBinding
import com.shantanu.example.modelbasic.network.data.local.User
import com.shantanu.example.modelbasic.ui.viewmodel.FirebaseBaseViewModel
import com.shantanu.example.modelbasic.utils.Helper

class RegisterFragment : Fragment() {
    private lateinit var model: FirebaseBaseViewModel
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userData: MutableLiveData<User>
    private var success: Boolean = false


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


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //for user registration
        binding.fragmentRegisterationButtonRegister.setOnClickListener {
            registerUser()
        }

    }

    //user registration view model
    private fun registerUser() {
        var pass = binding.fragmentRegisterationEdittextPassword.text.toString()
        var email = binding.fragmentRegisterationEdittextEmail.text.toString()

        var checkEmail = Helper.validateEmail(email)
        var checkPassword = Helper.validatePassword(pass)

        if (checkEmail && checkPassword) {

            model.registerUser(email, pass).observe(this, Observer {
                success = it
                Log.v("rs", success.toString())
                if (success) {
                    Toast.makeText(
                        activity,
                        "Registration Successful:" + " " + "check your inbox for email verification " + success.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.v("rs", success.toString())
                    findNavController().popBackStack()


                } else {
                    Toast.makeText(activity, "Registration failed", Toast.LENGTH_SHORT).show()

                    Log.v("rs", success.toString())

                }

            })

        } else if (!checkEmail) {
            binding.fragmentRegisterationEdittextEmail.requestFocus()
            binding.fragmentRegisterationEdittextEmail.text.clear()
            binding.fragmentRegisterationEdittextEmail.setError(
                "Please enter a valid email",
                resources.getDrawable(R.drawable.ic_arrow_back)
            )

        } else {
            binding.fragmentRegisterationEdittextPassword.requestFocus()
            binding.fragmentRegisterationEdittextPassword.text.clear()
            binding.fragmentRegisterationEdittextPassword.setError(
                "Password must contain minimum 8 character",
                resources.getDrawable(R.drawable.ic_arrow_back)
            )

        }


    }


}