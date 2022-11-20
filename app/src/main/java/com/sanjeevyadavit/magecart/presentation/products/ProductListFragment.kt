package com.sanjeevyadavit.magecart.presentation.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sanjeevyadavit.magecart.common.components.StateContainer
import com.sanjeevyadavit.magecart.ui.products.ProductList
import com.sanjeevyadavit.magecart.presentation.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    val args: ProductListFragmentArgs by navArgs()
    private val activityViewModel: MainActivityViewModel by activityViewModels<MainActivityViewModel>()

    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getProducts(args.categoryId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                StateContainer(state = viewModel.state.value) {
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
                        storeConfigs = activityViewModel.storeConfigs.value
                    )
                }
            }
        }
    }
}