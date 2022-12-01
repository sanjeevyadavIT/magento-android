package com.sanjeevyadavit.magecart.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sanjeevyadavit.magecart.domain.model.HomeScreenSkeletonData
import com.sanjeevyadavit.magecart.domain.model.Slider

@Composable
fun Home(data: HomeScreenSkeletonData, baseMediaUrl: String?) {
    Column() {
        Carousel(data.sliders, baseMediaUrl)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carousel(sliders: List<Slider>, baseMediaUrl: String?) {
    val pagerState = rememberPagerState()

    HorizontalPager(count = sliders.size, state = pagerState, modifier = Modifier.fillMaxWidth().height(200.dp)) { index ->
        val item = sliders[index]

        AsyncImage(
            model = "${baseMediaUrl}${item.image}",
            modifier = Modifier.height(200.dp),
            contentDescription = item.title
        )
    }

}