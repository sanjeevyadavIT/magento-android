package com.sanjeevyadavit.magecart.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sanjeevyadavit.magecart.common.model.IState
import com.sanjeevyadavit.magecart.domain.model.HomeScreenSkeletonData
import com.sanjeevyadavit.magecart.domain.model.Product
import com.sanjeevyadavit.magecart.domain.model.Slider

@Composable
fun Home(
    data: HomeScreenSkeletonData,
    featuredCategoryState: HashMap<Int, IState<List<Product>>>,
    baseMediaUrl: String?,
    onProductClick: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Carousel(data.sliders, baseMediaUrl)
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carousel(sliders: List<Slider>, baseMediaUrl: String?) {
    val pagerState = rememberPagerState()

    HorizontalPager(count = sliders.size, state = pagerState, modifier = Modifier.fillMaxWidth().height(200.dp)) { index ->
        val item = sliders[index]

        AsyncImage(
            model = if(item.image.startsWith("http")) item.image else "${baseMediaUrl}${item.image}",
            modifier = Modifier.height(200.dp).fillMaxWidth(),
            contentScale = ContentScale.Crop,
            contentDescription = item.title
        )
    }

}