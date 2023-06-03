package com.sanjeevyadavit.magecart.presentation.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sanjeevyadavit.magecart.common.ProductType
import com.sanjeevyadavit.magecart.domain.model.Cart
import com.sanjeevyadavit.magecart.domain.model.CartItem

@Composable
fun CartUI(data: Cart?) {
    data?.items?.let { CartList(it) }
}

@Composable
fun CartList(items: List<CartItem>) {
    LazyColumn {
        items(items.size) { index ->
            CartListItem(items[index])
            Divider()
        }
    }
}

@Composable
fun CartListItem(cartItem: CartItem) {
    // Display the cart item details in a row
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        AsyncImage(
            model = "https://placehold.jp/3d4070/ffffff/100x100.png", // TODO: @SANJEEV replace the placeholder image with correct image of the product
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit,
            contentDescription = cartItem.name
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = "Name: ${cartItem.name}")
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Qty: ${cartItem.quantity}")
                Spacer(modifier = Modifier.width(16.dp))

                Text(text = "Price: ${cartItem.price}") // TODO: @SANJEEV add correct currency symbol
            }
        }

    }
}

@Preview
@Composable
fun CartItemRowPreview() {
    val sampleCartItem = CartItem(
        itemId = 1,
        sku = "SKU123",
        quantity = 2,
        name = "Product 1",
        price = 10,
        productType = ProductType.SIMPLE,
        quoteId = 1001
    )
    CartListItem(cartItem = sampleCartItem)
}