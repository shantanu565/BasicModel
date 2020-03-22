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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

        /*val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setTheme(R.style.SplashTheme)
                .setAvailableProviders(providers)
                .build(),
            0
        )*/

        /* activity?.let {
             model?.getUser()?.observe(it, Observer {
                 Toast.makeText(activity, "Hello:" + it.name + " " , Toast.LENGTH_SHORT)
                     .show()



             })
         }*/

        binding.fragmentLoginButtonLogin.setOnClickListener {
            userLogin()
        }

        binding.fragmentLoginTextviewGoToRegister.setOnClickListener {
            findNavController().navigate(R.id.fragment_register)
        }

        binding.fragmentLoginTextviewForgetPassword.setOnClickListener {
            findNavController().navigate(R.id.fragment_forget_password)
        }

        binding.fragmentLoginImageviewLogo.setOnClickListener {
            scaler()
        }

        binding.fragmentLoginTextviewSeeGift.setOnClickListener {

        }

        binding.fragmentLoginTextviewSeeGift.setOnClickListener {
            binding.fragmentLoginImageviewGift.visibility = View.VISIBLE
            colorizer()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // observeViewModel(model)
        checkAuthentication()
    }

    private fun checkAuthentication() {


    }

    private fun userLogin() {

        var email = binding.fragmentLoginEdittextUsername.text.toString()
        var pass = binding.fragmentLoginEdittextPassword.text.toString()

        Helper.validateEmail(email)
        Helper.validatePassword(pass)

        // model.userLogin(email,pass)
        findNavController().navigate(R.id.fragment_home)
        //findNavController().popBackStack(R.id.fragment_home,true)

        /*  firebaseAuth!!.signInWithEmailAndPassword(pass!!, email!!)
              .addOnCompleteListener(activity!!) { task ->
                  //mProgressBar!!.hide()
                  if (task.isSuccessful) {
                      // Sign in success, update UI with signed-in user's information
                     // Log.d(TAG, "signInWithEmail:success")
                      updateUI()
                  } else {
                      // If sign in fails, display a message to the user.
                      //Log.e(TAG, "signInWithEmail:failure", task.exception)
                      Toast.makeText(activity, "Authentication failed.",
                          Toast.LENGTH_SHORT).show()
                  }
              }*/

    }

    private fun updateUI() {
        findNavController().navigate(R.id.fragment_home)
    }

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

    private fun colorizer() {
        var animator = ObjectAnimator.ofArgb(
            binding.fragmentLoginImageviewGift.parent,
            "backgroundColor", Color.WHITE, Color.BLUE, Color.RED, Color.WHITE
        )
        animator.setDuration(500)
        animator.repeatCount = 3
        animator.repeatMode = ObjectAnimator.REVERSE
        //animator.disableViewDuringAnimation(bgColor)
        animator.start()
        binding.fragmentLoginImageviewGift.visibility = View.GONE
    }


    fun observeViewModel(viewModel: LoginViewModel?) {


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
    }


}

