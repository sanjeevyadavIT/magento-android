package com.sanjeevyadavit.magecart.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
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

    Column(modifier = modifier) {
        Box(modifier = Modifier.weight(1f)) {
            HorizontalPager(count = data.size, state = pagerState, modifier = modifier) { index ->
                val item = data[index]

                AsyncImage(
                    model = item.imageUrl,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = contentScale,
                    contentDescription = item.title
                )
            }
            if (data.size > 1) {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                ) {
                    for (i in data.indices) {
                        val dotColor =
                            if (i == pagerState.currentPage) Color.Gray else Color.LightGray
                        Box(
                            modifier = Modifier
                                .size(15.dp)
                                .padding(4.dp)
                                .background(color = dotColor, shape = CircleShape)
                                .shadow(elevation = 2.dp, shape = CircleShape)
                        )
                    }
                }
            }

        }

    }
}

@Preview
@Composable
fun CarouselPreview() {
    val carouselItems = listOf(
        CarouselItemModel("Image 1", "https://placehold.jp/3d4070/ffffff/150x150.png"),
        CarouselItemModel("Image 2", "https://placehold.jp/3d4070/ff00ff/150x150.png"),
        CarouselItemModel("Image 3", "https://placehold.jp/3d4070/00ffff/150x150.png")
    )

    Carousel(data = carouselItems)
}