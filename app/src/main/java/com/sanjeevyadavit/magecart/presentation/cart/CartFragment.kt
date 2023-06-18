package com.sanjeevyadavit.magecart.presentation.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sanjeevyadavit.magecart.presentation.cart.components.CartUI

class CartFragment : Fragment() {

    private val cartViewModel: CartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navController = findNavController()
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Surface {
                        val state = cartViewModel.cart.value
                        CartUI(state) { sku, productName ->
                            val action = CartFragmentDirections.actionCartFragmentToProductDetailFragment(sku, productName)
                            navController.navigate(action)
                        }
                    }
                }
            }
        }
    }
}