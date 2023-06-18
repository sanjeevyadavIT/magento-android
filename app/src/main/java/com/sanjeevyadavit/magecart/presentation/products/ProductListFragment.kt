package com.sanjeevyadavit.magecart.presentation.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sanjeevyadavit.magecart.common.components.StateContainer
import com.sanjeevyadavit.magecart.presentation.MainActivityViewModel
import com.sanjeevyadavit.magecart.ui.products.ProductList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {
    private val args: ProductListFragmentArgs by navArgs()
    private val activityViewModel: MainActivityViewModel by activityViewModels<MainActivityViewModel>()

    private val viewModel: ProductListViewModel by viewModels()

    // TODO: Set Toolbar title as args.categoryName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val navController = findNavController()
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                StateContainer(state = viewModel.state.value) { it ->
                    ProductList(
                        products = it,
                        loadMore = viewModel.loadMore.value,
                        totalCount = viewModel.getTotalCount(),
                        onScroll = { i: Int -> viewModel.onChangeProductListScrollPosition(i) },
                        fetchMore = { viewModel.fetchNextPage() },
                        /**
                         * Question: This storeConfigs need to be access across fragment and is present in MainActivityViewModel
                         * what is the best way to access this data in different fragment, right now I am creating instance of MainActivityViewModel
                         */
                        baseMediaUrl = activityViewModel.storeConfigs.value?.baseMediaUrl
                    ) { item ->
                        val action =
                            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                                item.sku,
                                item.name
                            )
                        navController.navigate(action)
                    }
                }
            }
        }
    }
}