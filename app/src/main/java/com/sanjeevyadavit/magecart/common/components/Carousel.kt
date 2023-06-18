package com.sanjeevyadavit.magecart.common.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sanjeevyadavit.magecart.common.model.CarouselItemModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carousel(
    data: List<CarouselItemModel>,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(250.dp),
    contentScale: ContentScale = ContentScale.Crop
) {
    val pagerState = rememberPagerState()

    HorizontalPager(count = data.size, state = pagerState, modifier = modifier) { index ->
        val item = data[index]

        AsyncImage(
            model = item.imageUrl,
            modifier = Modifier.fillMaxSize(),
            contentScale = contentScale,
            contentDescription = item.title
        )
    }

}