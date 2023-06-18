package com.sanjeevyadavit.magecart.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sanjeevyadavit.magecart.common.components.Carousel
import com.sanjeevyadavit.magecart.common.model.CarouselItemModel
import com.sanjeevyadavit.magecart.common.model.IState
import com.sanjeevyadavit.magecart.domain.model.HomeScreenSkeletonData
import com.sanjeevyadavit.magecart.domain.model.Product

@Composable
fun Home(
    data: HomeScreenSkeletonData,
    featuredCategoryState: HashMap<Int, IState<List<Product>>>,
    baseMediaUrl: String?,
    onProductClick: (Product) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Carousel(data.sliders.map {
            CarouselItemModel(
                it.title,
                if (it.image.startsWith("http")) it.image else "${baseMediaUrl}${it.image}"
            )
        })
        data.featuredCategories.map {
            HorizontalProductList(
                data = it,
                featuredCategoryState = featuredCategoryState,
                baseMediaUrl = baseMediaUrl,
                onClick = onProductClick
            )
        }
    }
}
