package com.sanjeevyadavit.magecart.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.navArgs
import com.sanjeevyadavit.magecart.viewmodel.ProductListViewModel
import com.sanjeevyadavit.magecart.viewmodel.ProductListViewModelFactory

class ProductListFragment : Fragment() {

    val args: ProductListFragmentArgs by navArgs()
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
                if(products != null ){
                    viewModel.products.value?.let { ProductList(products = it) }
                } else {
                    CircularProgressIndicator()
                }
            }
        }
    }
}