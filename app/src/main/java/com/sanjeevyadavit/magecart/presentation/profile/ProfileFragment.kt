package com.sanjeevyadavit.magecart.presentation.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.sanjeevyadavit.magecart.presentation.MainActivity
import com.sanjeevyadavit.magecart.R
import com.sanjeevyadavit.magecart.presentation.login.LoginFragment
import com.sanjeevyadavit.magecart.presentation.profile.components.Profile


class ProfileFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPreferences =
            requireActivity().getSharedPreferences(
                MainActivity.SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        val navController = findNavController()
        return ComposeView(requireContext()).apply {
            setContent {
                Profile {
                    logout(navController)
                }
            }
        }
    }

    private fun logout(navController: NavController) {
        with(sharedPreferences.edit()) {
            putString(LoginFragment.CUSTOMER_TOKEN, null)
            apply()
        }
        Toast.makeText(context, "Logout Successfully!", Toast.LENGTH_SHORT).show()
        navController.navigate(R.id.homeFragment)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Profile{}
}