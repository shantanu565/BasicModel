package com.shantanu.example.modelbasic.ui.feature.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var model: RegistrationViewModel
    private lateinit var binding:FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false
        )
        model = ViewModelProviders.of(this)[RegistrationViewModel::class.java]

        binding.fragmentRegisterationTextviewBackToLogin.setOnClickListener {
            //findNavController().navigate(R.id.action_fragment_registeration_to_fragment_login)
        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


}