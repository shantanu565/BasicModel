package com.shantanu.example.modelbasic.ui.feature.login

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUserMetadata
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.databinding.FragmentLoginBinding
import com.shantanu.example.modelbasic.network.firebase.FirebaseUserLiveData
import com.shantanu.example.modelbasic.ui.viewmodel.FirebaseBaseViewModel
import com.shantanu.example.modelbasic.utils.Helper

class LoginFragment : Fragment() {
    private lateinit var model: FirebaseBaseViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var succesLogin: Boolean = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        model = ViewModelProviders.of(this)[FirebaseBaseViewModel::class.java]
        binding.lifecycleOwner = activity
        binding.model = model

        firebaseAuth = FirebaseAuth.getInstance()


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //calling firebase login method on button click
        binding.fragmentLoginButtonLogin.setOnClickListener {
            userLogin()
        }

        //registration screen
        binding.fragmentLoginTextviewGoToRegister.setOnClickListener {
            findNavController().navigate(R.id.fragment_register)
        }

        //forget password screen
        binding.fragmentLoginTextviewForgetPassword.setOnClickListener {
            findNavController().navigate(R.id.fragment_forget_password)
        }

        //logo icon scaler animation
        binding.fragmentLoginImageviewLogo.setOnClickListener {
            scaler()
        }


    }


    //user login viewmodel method
    private fun userLogin() {

        var email = binding.fragmentLoginEdittextUsername.text.toString().trim()
        var pass = binding.fragmentLoginEdittextPassword.text.toString()

        var validateEmail = Helper.validateEmail(email)
        var validatePassword = Helper.validatePassword(pass)

        if (validateEmail && validatePassword) {
            model.userLogin(email, pass).observe(this, Observer {
                succesLogin = it
                if (succesLogin) {
                    //login success
                    Toast.makeText(activity, succesLogin.toString(), Toast.LENGTH_SHORT).show()
                    binding.fragmentLoginEdittextPassword.text.clear()
                    binding.fragmentLoginEdittextUsername.text.clear()
                    findNavController().navigate(R.id.fragment_home)
                    findNavController().popBackStack()
                    findNavController().navigateUp()

                } else {
                    //if login failed from server
                    Toast.makeText(activity, "Login failed.Try again", Toast.LENGTH_SHORT).show()
                    binding.fragmentLoginEdittextPassword.text.clear()
                    binding.fragmentLoginEdittextUsername.text.clear()


                }

            })

        } else if (validateEmail == false) {
            //email field validation error
            binding.fragmentLoginEdittextUsername.requestFocus()
            binding.fragmentLoginEdittextUsername.text.clear()
            binding.fragmentLoginEdittextUsername.setError(
                "Please enter a valid email",
                resources.getDrawable(R.drawable.ic_arrow_back)
            )


        } else {
            //password field validation error
            binding.fragmentLoginEdittextPassword.requestFocus()
            binding.fragmentLoginEdittextPassword.text.clear()
            binding.fragmentLoginEdittextPassword.setError(
                "Password must contain 8 character",
                resources.getDrawable(R.drawable.ic_arrow_back)
            )

        }

    }

    //logo animation
    private fun scaler() {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(
            binding.fragmentLoginImageviewLogo, scaleX, scaleY
        )
        animator.repeatCount = 3
        animator.repeatMode = ObjectAnimator.REVERSE
        //animator.disableViewDuringAnimation(scaleButton)
        animator.start()
    }


    /*  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
          super.onActivityResult(requestCode, resultCode, data)
          if (requestCode == 0) {
              val response = IdpResponse.fromResultIntent(data)
              if (resultCode == Activity.RESULT_OK) {
                  //if success
                  findNavController().navigate(R.id.fragment_home)

                  Log.i(
                      "tag",
                      "Successfully signed in user ${FirebaseAuth.getInstance().currentUser?.displayName}!"
                  )
              } else {

                  Log.i("tag", "Sign in unsuccessful ${response?.error?.errorCode}")
              }
          }
      }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //controlling back button functionality
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() { // Handle the back button event
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }

}

