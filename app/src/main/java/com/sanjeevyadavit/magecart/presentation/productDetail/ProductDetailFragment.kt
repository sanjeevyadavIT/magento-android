package com.sanjeevyadavit.magecart.presentation.productDetail

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sanjeevyadavit.magecart.common.components.StateContainer
import com.sanjeevyadavit.magecart.presentation.MainActivity
import com.sanjeevyadavit.magecart.presentation.MainActivityViewModel
import com.sanjeevyadavit.magecart.presentation.productDetail.components.ProductDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private val args: ProductDetailFragmentArgs by navArgs()
    private val activityViewModel: MainActivityViewModel by activityViewModels<MainActivityViewModel>()

    private val viewModel: ProductDetailViewModel by viewModels()

    // TODO: Set toolbar title as args.productName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // QUESTION: Is it a good practice to access value of something which was calculated in activity
        // or I have access to MainActivityViewModel should I ask isLoggedIn from there?
        val customerToken = (activity as MainActivity)?.getCustomerToken()
        val isLoggedIn = customerToken != null
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Surface {
                        val state = viewModel.state.value
                        val addToCartApiStatus = viewModel.addToCartStatus.value

                        // QUESTION: Is this good practice to make API call like this?
                        LaunchedEffect(state) {
                            state.data?.configurableOptions?.forEach {
                                if (activityViewModel.attributeMap.value.get(it.attributeId.toInt()) == null) {
                                    activityViewModel.fetchAttributeData(it.attributeId.toInt())
                                }
                            }
                        }

                        StateContainer(state = state) {
                            ProductDetail(
                                it,
                                baseMediaUrl = activityViewModel.storeConfigs.value?.baseMediaUrl,
                                attributeMap = activityViewModel.attributeMap.value,
                                isLoggedIn = isLoggedIn,
                                addToCartApiStatus,
                            ) {
                                activityViewModel.cart.value?.id?.let { cartId ->
                                    viewModel.addItemToCart(
                                        customerSessionToken = customerToken!!,
                                        sku = it,
                                        cartId = cartId
                                    )
                                } ?: kotlin.run {
                                    Toast.makeText(
                                        context,
                                        "Cart is not created",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}