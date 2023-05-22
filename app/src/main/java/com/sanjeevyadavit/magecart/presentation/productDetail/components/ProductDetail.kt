package com.sanjeevyadavit.magecart.presentation.productDetail.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sanjeevyadavit.magecart.common.ProductType
import com.sanjeevyadavit.magecart.common.components.Carousel
import com.sanjeevyadavit.magecart.common.components.HtmlText
import com.sanjeevyadavit.magecart.common.model.CarouselItemModel
import com.sanjeevyadavit.magecart.common.model.IState
import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.domain.model.AttributeData
import com.sanjeevyadavit.magecart.domain.model.ConfigurableOption
import com.sanjeevyadavit.magecart.domain.model.ProductDetail

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetail(
    product: ProductDetail,
    baseMediaUrl: String?,
    attributeMap: HashMap<Int, AttributeData>,
    isLoggedIn: Boolean,
    addToCartApiStatus: IState<Boolean>,
    addItemToCart: (sku: String) -> Unit
) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val sliders =
        product.mediaList?.map { CarouselItemModel(it, "${baseMediaUrl}catalog/product/${it}") }
            ?: if (product.thumbnailUrl != null) listOf(
                CarouselItemModel(
                    product.name,
                    "${baseMediaUrl}catalog/product/${product.thumbnailUrl}"
                )
            ) else null

    LaunchedEffect(addToCartApiStatus) {
        if (addToCartApiStatus is Resource.Success<*>) {
            scaffoldState.snackbarHostState.showSnackbar("Item added to Cart!")
        }
    }

    val showToast =
        { message: String -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }

    val addToCart = {
        if (!isLoggedIn) {
            // TODO: Take user to Login flow
            showToast("You are not logged in")
        } else if (product.productType != ProductType.SIMPLE) {
            // TODO@SANJEEV Add Support for CONFIGURABLE TYPE PRODUCT
            showToast("${product.productType} is not yet supported")
        } else {
            addItemToCart(product.sku)
        }

    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
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
            product.configurableOptions?.let {
                it.map { option ->
                    ConfigurableOptionUI(option, attributeMap[option.attributeId.toInt()])
                }
            }
            Button(onClick = addToCart, enabled = addToCartApiStatus.isLoading) {
                if (addToCartApiStatus.isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.primary
                    )
                } else {
                    Text(text = "Add to Cart")
                }

            }
        }
    }

}

// TODO: Extract the simple product when user chooses configurable options
// TODO: Implement Add to Cart functionality
@Composable
fun ConfigurableOptionUI(option: ConfigurableOption, attributeExtraData: AttributeData?) {

    // Define the currently selected item
    // QUESTION: Is it a good practice to have two state, or these should be merge in single State with Pair ?
    var selectedItem by remember { mutableStateOf<Int?>(null) }
    var selectedItemLabel by remember { mutableStateOf<String?>(null) }

    // Define whether the dropdown menu is expanded or not
    var expanded by remember { mutableStateOf(false) }

    // Create a DropdownMenu that shows the items when expanded
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        // Create a DropdownMenuItem for each item
        option.values.forEach { item ->
            DropdownMenuItem(
                text = {
                    Text(text = "${attributeExtraData?.options?.get(item.toString()) ?: item}")
                },
                onClick = {
                    selectedItem = item
                    selectedItemLabel =
                        "${attributeExtraData?.options?.get(item.toString()) ?: item}"
                    expanded = false
                })
        }
    }

    // Show the currently selected item
    Text(text = "${option.label}", style = MaterialTheme.typography.subtitle1)

    // Show the dropdown toggle button
    OutlinedButton(onClick = { expanded = true }) {
        Text(text = if (selectedItemLabel == null) "Select: ${option.label} " else "Selected : $selectedItemLabel")
        Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Localized description")
    }
}
