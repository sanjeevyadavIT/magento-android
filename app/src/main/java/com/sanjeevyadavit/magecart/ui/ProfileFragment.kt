package com.sanjeevyadavit.magecart.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sanjeevyadavit.magecart.MainActivity
import com.sanjeevyadavit.magecart.R


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
        return ComposeView(requireContext()).apply {
            setContent {
                Profile(sharedPreferences)
            }
        }
    }
}

fun logout(context: Context, sharedPreferences: SharedPreferences, navController: NavController) {
    with(sharedPreferences.edit()) {
        putString(LoginFragment.CUSTOMER_TOKEN, null)
        apply()
    }
    Toast.makeText(context, "Logout Successfully!", Toast.LENGTH_SHORT).show()
    // TODO:@sanjeev This doesn't work, cannot navigate to navigation from compose to outside nav_graph which is not in compose
//    navController.navigate(R.id.homeFragment)
}

@Composable
fun Profile(sharedPreferences: SharedPreferences) {
    val context = LocalContext.current
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.DarkGray)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Profile photo",
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.White)
                        .border(1.dp, Color.DarkGray),
                )
                Text(text = stringResource(R.string.greetings_user))
            }

        }
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedButton(onClick = {
            logout(context, sharedPreferences, navController)
        }) {
            Text(text = "Logout")
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Profile()
//}