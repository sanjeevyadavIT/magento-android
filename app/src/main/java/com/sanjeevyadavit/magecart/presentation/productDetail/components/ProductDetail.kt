package com.sanjeevyadavit.magecart.presentation.productDetail.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sanjeevyadavit.magecart.common.components.Carousel
import com.sanjeevyadavit.magecart.common.components.HtmlText
import com.sanjeevyadavit.magecart.common.model.CarouselItemModel
import com.sanjeevyadavit.magecart.domain.model.ProductDetail

@Composable
fun ProductDetail(product: ProductDetail, baseMediaUrl: String?) {
    val context = LocalContext.current
    val sliders = product.mediaList?.map { CarouselItemModel(it, "${baseMediaUrl}catalog/product/${it}") }
        ?: if (product.thumbnailUrl != null) listOf(
            CarouselItemModel(
                product.name,
                "${baseMediaUrl}catalog/product/${product.thumbnailUrl}"
            )
        ) else null
    sliders?.let {
        Carousel(
            data = it,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Fit
        )
    }
    Text(text = product.name, style = MaterialTheme.typography.h6)
    product.description?.let { HtmlText(html = it) }
    // TODO: If product type configurable show options
    Button(onClick = { Toast.makeText(context, "WIP", Toast.LENGTH_SHORT).show()}) {
        Text(text = "Add to Cart")
    }
}