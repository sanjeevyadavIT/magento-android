package com.sanjeevyadavit.magecart.ui.products

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sanjeevyadavit.magecart.model.Constants
import com.sanjeevyadavit.magecart.model.product.ProductData
import com.sanjeevyadavit.magecart.model.product.Products
import com.sanjeevyadavit.magecart.service.RetrofitClient

@Composable
fun ProductList(products: Products){
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        itemsIndexed(products?.items) { _, item ->
            ProductListItem(product = item)
        }
    }
}

@Composable
fun ProductListItem(product: ProductData){

    val thumbnail = product.customAttributes.find {
        it.attributeCode == Constants.THUMBNAIL_SK
    }?.value as String

    Column() {
        AsyncImage(
            model = "${RetrofitClient.MEDIA_URL}${thumbnail}",
            modifier = Modifier.height(100.dp),
            contentDescription = "Product Image"
        )
        Text(text = product.name)
    }
}