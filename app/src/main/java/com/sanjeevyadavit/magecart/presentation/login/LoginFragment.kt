package com.sanjeevyadavit.magecart.presentation.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.sanjeevyadavit.magecart.presentation.MainActivity
import com.sanjeevyadavit.magecart.R
import com.sanjeevyadavit.magecart.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        viewModel.state.observe(viewLifecycleOwner) {
            it.data?.let {
                saveCustomerTokenInPreference(it)
                findNavController().popBackStack()
            }
        }
    }

    private fun saveCustomerTokenInPreference(token: String) {
        with(sharedPreferences.edit()) {
            putString(CUSTOMER_TOKEN, token)
            apply()
        }
    }

    private fun setupClickListeners() {
        binding.signupCta.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            it.findNavController().navigate(action)
        }
    }
}