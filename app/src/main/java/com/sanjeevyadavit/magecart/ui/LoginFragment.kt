package com.sanjeevyadavit.magecart.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.sanjeevyadavit.magecart.MainActivity
import com.sanjeevyadavit.magecart.R
import com.sanjeevyadavit.magecart.databinding.FragmentLoginBinding
import com.sanjeevyadavit.magecart.viewmodel.LoginViewModel
import com.sanjeevyadavit.magecart.viewmodel.MainActivityViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val CUSTOMER_TOKEN = "CUSTOMER_TOKEN"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        sharedPreferences =
            requireActivity().getSharedPreferences(MainActivity.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.customerToken.observe(viewLifecycleOwner) {
            it?.let {
                with(sharedPreferences.edit()) {
                    putString(CUSTOMER_TOKEN, it)
                    apply()
                }
                findNavController().popBackStack()
            }
        }
    }

    private fun setupClickListeners() {
        binding.signupCta.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            it.findNavController().navigate(action)
        }
    }
}