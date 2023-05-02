package com.sanjeevyadavit.magecart.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sanjeevyadavit.magecart.common.components.ProductListItem
import com.sanjeevyadavit.magecart.common.model.IState
import com.sanjeevyadavit.magecart.common.components.StateContainer
import com.sanjeevyadavit.magecart.domain.model.FeaturedCategory
import com.sanjeevyadavit.magecart.domain.model.Product
import com.sanjeevyadavit.magecart.domain.model.StoreConfigs


@Composable
fun HorizontalProductList(
    data: FeaturedCategory,
    featuredCategoryState: HashMap<Int, IState<List<Product>>>,
    baseMediaUrl: String?
) {
    featuredCategoryState[data.categoryId]?.let {
        Text(text = data.title)
        StateContainer(state = it) { list ->
            LazyRow {
                items(list.size) { index ->
                    ProductListItem(product = list[index], baseMediaUrl = baseMediaUrl, customWidth = 80.dp)
                }
            }
        }   
    }
}