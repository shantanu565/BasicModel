package com.shantanu.example.modelbasic.ui.feature.login

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var model: LoginViewModel
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        model = ViewModelProviders.of(this)[LoginViewModel::class.java]
        binding.lifecycleOwner=activity
        binding.model=model

        activity?.let {
            model?.getUser()?.observe(it, Observer {
                Toast.makeText(activity,"Hello:" +it.name+" "+it.pass,Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.fragment_home)
                //findNavController().navigate(R.id.action_fragment_login_to_fragment_home)
            })
        }

        binding.fragmentLoginTextviewGoToRegister.setOnClickListener {
            //findNavController().navigate(R.id.action_fragment_login_to_fragment_registeration)
        }

        binding.fragmentLoginTextviewClickIcon.setOnClickListener {
            scaler()

        }

        binding.fragmentLoginTextviewSeeGift.setOnClickListener {

        }

        binding.fragmentLoginTextviewSeeGift.setOnClickListener {
            binding.fragmentLoginImageviewGift.visibility=View.VISIBLE
            colorizer()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeViewModel(model)


    }

    private fun scaler() {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(
            binding.fragmentLoginImageviewLogo, scaleX, scaleY)
        animator.repeatCount = 3
        animator.repeatMode = ObjectAnimator.REVERSE
        //animator.disableViewDuringAnimation(scaleButton)
        animator.start()
    }

    private fun colorizer() {
        var animator = ObjectAnimator.ofArgb(binding.fragmentLoginImageviewGift.parent,
            "backgroundColor", Color.WHITE,Color.BLUE, Color.RED,Color.WHITE)
        animator.setDuration(500)
        animator.repeatCount = 3
        animator.repeatMode = ObjectAnimator.REVERSE
        //animator.disableViewDuringAnimation(bgColor)
        animator.start()
        binding.fragmentLoginImageviewGift.visibility=View.GONE
    }



    fun observeViewModel(viewModel: LoginViewModel?){


    }


}