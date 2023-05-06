package com.sanjeevyadavit.magecart.presentation.productDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sanjeevyadavit.magecart.common.components.StateContainer
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
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Surface {
                        val state = viewModel.state.value

                        StateContainer(state = state) {
                            ProductDetail(it, baseMediaUrl = activityViewModel.storeConfigs.value?.baseMediaUrl)
                        }
                    }
                }
            }
        }
    }
}