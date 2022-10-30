package com.sanjeevyadavit.magecart.ui.products

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sanjeevyadavit.magecart.model.Constants
import com.sanjeevyadavit.magecart.model.StoreConfigs
import com.sanjeevyadavit.magecart.model.product.ProductData
import com.sanjeevyadavit.magecart.model.product.Products
import com.sanjeevyadavit.magecart.service.RetrofitClient
import com.sanjeevyadavit.magecart.viewmodel.ProductListViewModel

@Composable
fun ProductList(viewModel: ProductListViewModel, storeConfigs: StoreConfigs? = null){
    val loadMore = viewModel.loadMore.value
    val products = viewModel.products.value

    Column {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            itemsIndexed(products) { index, item ->
                // Question: This pagination code, does it make sense to be here?
                viewModel.onChangeProductListScrollPosition(index)
                if(index == products.size - 1 && (index + 1) < viewModel.getTotalCount() && !loadMore){
                    viewModel.fetchNextPage()
                }
                //Question: Is there any way to avoid prop drilling for storeConfigs
                ProductListItem(product = item, storeConfigs=storeConfigs)
            }
        }
        if(loadMore) {
            // FIXME: This progress bar is not showing
            CircularProgressIndicator(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ProductListItem(product: ProductData, storeConfigs: StoreConfigs? = null){

    val thumbnail = product.customAttributes.find {
        it.attributeCode == Constants.THUMBNAIL_SK
    }?.value as String

    Column(
        Modifier
            .border(1.dp, Color.LightGray)
            .heightIn(min = 240.dp)) {
        AsyncImage(
            model = "${storeConfigs?.baseMediaUrl}catalog/product/${thumbnail}",
            modifier = Modifier.height(200.dp),
            contentDescription = "Product Image"
        )
        Text(text = product.name)
    }
}