package com.sanjeevyadavit.magecart.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sanjeevyadavit.magecart.viewmodel.MainActivityViewModel
import com.sanjeevyadavit.magecart.viewmodel.ProductListViewModel
import com.sanjeevyadavit.magecart.viewmodel.ProductListViewModelFactory

class ProductListFragment : Fragment() {

    val args: ProductListFragmentArgs by navArgs()
    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private val viewModel: ProductListViewModel by viewModels {
        ProductListViewModelFactory(args.categoryId, this.requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                val products = viewModel.products.value

                /**
                 * Question: This storeConfigs need to be access across fragment and is present in MainActivityViewModel
                 * what is the best way to access this data in different fragment, right now I am creating instance of MainActivityViewModel
                 */
                val storeConfigs = activityViewModel.storeConfigs
                if(products != null ){
                    viewModel.products.value?.let { ProductList(products = it,storeConfigs = storeConfigs) }
                } else {
                    CircularProgressIndicator()
                }
            }
        }
    }
}