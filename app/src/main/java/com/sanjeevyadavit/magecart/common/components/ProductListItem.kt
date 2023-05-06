package com.sanjeevyadavit.magecart.common.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sanjeevyadavit.magecart.domain.model.Product
import com.sanjeevyadavit.magecart.domain.model.StoreConfigs

@Composable
fun ProductListItem(product: Product, baseMediaUrl: String?, customWidth: Dp? = null, onClick: (String) -> Unit) {
    val containerModifier = Modifier
        .border(1.dp, Color.LightGray)
        .heightIn(min = 240.dp)
    if (customWidth != null) {
        // FIXME: width of container is still dynamic and different for different item
        containerModifier.width(customWidth)
    }

    Column(
        modifier = containerModifier.clickable {
           onClick(product.sku)
        }
    ) {
        AsyncImage(
            model = "${baseMediaUrl}catalog/product/${product.thumbnailUrl}",
            modifier = Modifier.height(200.dp),
            contentDescription = "Product Image"
        )
        Text(text = product.name)
    }
}