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
import com.sanjeevyadavit.magecart.model.StoreConfigs
import com.sanjeevyadavit.magecart.model.product.ProductData
import com.sanjeevyadavit.magecart.model.product.Products
import com.sanjeevyadavit.magecart.service.RetrofitClient

@Composable
fun ProductList(products: Products, storeConfigs: StoreConfigs? = null){
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        itemsIndexed(products.items) { _, item ->
            //Question: Is there any way to avoid prop drilling for storeConfigs
            ProductListItem(product = item, storeConfigs=storeConfigs)
        }
    }
}

@Composable
fun ProductListItem(product: ProductData, storeConfigs: StoreConfigs? = null){

    val thumbnail = product.customAttributes.find {
        it.attributeCode == Constants.THUMBNAIL_SK
    }?.value as String

    Column() {
        AsyncImage(
            model = "${storeConfigs?.baseMediaUrl}catalog/product/${thumbnail}",
            modifier = Modifier.height(100.dp),
            contentDescription = "Product Image"
        )
        Text(text = product.name)
    }
}