package com.sanjeevyadavit.magecart.ui.categories

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sanjeevyadavit.magecart.R
import com.sanjeevyadavit.magecart.model.CategoryTree

@Composable
fun CategoryList(categories: List<CategoryTree>, useLazyList: Boolean = false, modifier: Modifier = Modifier) {
    val state = rememberLazyListState()

    if(useLazyList) {
        LazyColumn(
            state = state,
            modifier = modifier
        ) {
            itemsIndexed(categories) { index, _ ->
                if(!(index == 0 && useLazyList))Divider()
                CategoryListItem(categories[index])
            }
        }
    } else {
        Column(modifier = modifier) {
            for(category in categories) {
                Divider()
                CategoryListItem(category)
            }
        }
    }
}

@Composable
fun CategoryListItem(categoryItem: CategoryTree) {
    var isExpanded by remember { mutableStateOf(false) }
    val containsChildList = categoryItem.childrenData.isNotEmpty()
    val context = LocalContext.current

    val angle: Float by animateFloatAsState(
        if (isExpanded) -180F else 0F,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearOutSlowInEasing
        )
    )

    Column {
        Row(modifier = Modifier
            .clickable {
                if (containsChildList) {
                    isExpanded = !isExpanded
                } else {
                    // TODO: Open ProductListing for that category
                    Toast
                        .makeText(context, categoryItem.name, Toast.LENGTH_SHORT)
                        .show()
                }
            }
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Text(text = categoryItem.name)
            if (containsChildList) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "Drop down arrow",
                    modifier = Modifier.rotate(angle)
                )
            }
        }
        AnimatedVisibility(visible = isExpanded) {
            CategoryList(categories = categoryItem.childrenData, modifier = Modifier.padding(16.dp))
        }
    }
}