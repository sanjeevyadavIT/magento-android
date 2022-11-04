package com.sanjeevyadavit.magecart.ui.products

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sanjeevyadavit.magecart.data.remote.dto.StoreConfigsDto
import com.sanjeevyadavit.magecart.domain.model.Product
import com.sanjeevyadavit.magecart.domain.model.StoreConfigs

@Composable
fun ProductList(
    products: List<Product>,
    loadMore: Boolean,
    totalCount: Int,
    onScroll: (Int) -> Unit,
    fetchMore: () -> Unit,
    storeConfigs: StoreConfigs? = null
) {
    Column {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            itemsIndexed(products) { index, item ->
                // Question: This pagination code, does it make sense to be here?
                onScroll(index)
                if (index == products.size - 1 && (index + 1) < totalCount && !loadMore) {
                    fetchMore()
                }
                //Question: Is there any way to avoid prop drilling for storeConfigs
                ProductListItem(product = item, storeConfigs = storeConfigs)
            }
        }
        if (loadMore) {
            // FIXME: This progress bar is not showing
            CircularProgressIndicator(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ProductListItem(product: Product, storeConfigs: StoreConfigs? = null) {
    Column(
        Modifier
            .border(1.dp, Color.LightGray)
            .heightIn(min = 240.dp)
    ) {
        Log.d("SANJEEV", "${storeConfigs?.baseMediaUrl}catalog/product/${product.thumbnailUrl}")
        AsyncImage(
            model = "${storeConfigs?.baseMediaUrl}catalog/product/${product.thumbnailUrl}",
            modifier = Modifier.height(200.dp),
            contentDescription = "Product Image"
        )
        Text(text = product.name)
    }
}