package com.sanjeevyadavit.magecart.ui.categories

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sanjeevyadavit.magecart.R
import com.sanjeevyadavit.magecart.data.remote.dto.CategoriesDto
import com.sanjeevyadavit.magecart.domain.model.Category

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    categoryList: List<Category>,
    useLazyList: Boolean = false,
    onClick: (Category) -> Unit
) {
    // QUESTION: Can we write this code in better way?
    if(useLazyList) {
        LazyColumn(
            state = rememberLazyListState(),
            modifier = modifier
        ) {
            itemsIndexed(categoryList) { index, _ ->
                if(!(index == 0 && useLazyList)) Divider()
                CategoryListItem(categoryList[index], onClick = onClick)
            }
        }
    } else {
        Column(modifier = modifier) {
            for(category in categoryList) {
                Divider()
                CategoryListItem(category, onClick = onClick)
            }
        }
    }
}

@Composable
fun CategoryListItem(categoryItem: Category, onClick: (Category) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    val containsChildList = categoryItem.childrenData.isNotEmpty()

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
                  onClick(categoryItem)
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
            CategoryList(modifier = Modifier.padding(16.dp), categoryList = categoryItem.childrenData, onClick = onClick)
        }
    }
}