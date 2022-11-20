package com.sanjeevyadavit.magecart.presentation.categories

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sanjeevyadavit.magecart.common.components.StateContainer
import com.sanjeevyadavit.magecart.data.remote.dto.CategoriesDto
import com.sanjeevyadavit.magecart.domain.model.Category
import com.sanjeevyadavit.magecart.ui.categories.CategoryList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private val viewModel: CategoryListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navController = findNavController()
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Surface {
                        val state = viewModel.state.value

                        StateContainer(state = state) {
                            CategoryList(categoryList = it, useLazyList = true) { categoryId ->
                                val action = CategoriesFragmentDirections.actionCategoriesFragmentToProductListFragment(categoryId)
                                navController.navigate(action)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 720)
@Composable
fun CategoryListPreview() {
    val list = listOf(
        Category(
            id = 20,
            name = "Women",
            isActive = true,
            level = 2,
            productCount = 0,
            childrenData = listOf(
                Category(
                    id = 21,
                    name = "Tops",
                    isActive = true,
                    level = 3,
                    productCount = 0,
                    childrenData = listOf()
                )
            )
        )
    )
    CategoryList(categoryList = list, onClick = {})
}

@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 720,
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
fun CategoryListPreviewInDarkMode() {
    val list = listOf<Category>(
        Category(
            id = 20,
            name = "Women",
            isActive = true,
            level = 2,
            productCount = 0,
            childrenData = listOf()
        )
    )
    CategoryList(categoryList = list, onClick = {})
}