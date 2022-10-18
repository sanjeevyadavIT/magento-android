package com.sanjeevyadavit.magecart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sanjeevyadavit.magecart.R
import com.sanjeevyadavit.magecart.databinding.FragmentLoginBinding
import com.sanjeevyadavit.magecart.databinding.FragmentSignupBinding
import com.sanjeevyadavit.magecart.viewmodel.LoginViewModel
import com.sanjeevyadavit.magecart.viewmodel.SignupViewModel

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}