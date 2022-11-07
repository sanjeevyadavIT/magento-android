package com.sanjeevyadavit.magecart.ui.categories

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
import com.sanjeevyadavit.magecart.model.CategoryTree
import com.sanjeevyadavit.magecart.viewmodel.CategoriesViewModel

class CategoriesFragment : Fragment() {

    private val viewModel: CategoriesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Surface {
                        CategoryList(viewModel.categoryTree.value, useLazyList = true)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 720)
@Composable
fun preview() {
    val list = listOf(
        CategoryTree(
            id = 20,
            parentId = 2,
            name = "Women",
            isActive = true,
            position = 2,
            level = 2,
            productCount = 0,
            childrenData = listOf(
                CategoryTree(
                    id = 21,
                    parentId = 20,
                    name = "Tops",
                    isActive = true,
                    position = 2,
                    level = 3,
                    productCount = 0,
                    childrenData = listOf()
                )
            )
        )
    )
    CategoryList(categories = list)
}

@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 720,
    uiMode = UI_MODE_NIGHT_YES,
)

@Composable
fun previewInDarkMode() {
    val list = listOf<CategoryTree>(
        CategoryTree(
            id = 20,
            parentId = 2,
            name = "Women",
            isActive = true,
            position = 2,
            level = 2,
            productCount = 0,
            childrenData = listOf()
        )
    )
    CategoryList(categories = list)
}