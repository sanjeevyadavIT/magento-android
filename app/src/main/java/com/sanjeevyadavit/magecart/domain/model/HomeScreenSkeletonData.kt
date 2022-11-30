package com.sanjeevyadavit.magecart.domain.model

data class HomeScreenSkeletonData(
    val sliders: List<Slider>,
    val featuredCategories: List<FeaturedCategory>
)

data class Slider(
    val title: String,
    val image: String
)

data class FeaturedCategory(
    val categoryId: Int,
    val title: String
)