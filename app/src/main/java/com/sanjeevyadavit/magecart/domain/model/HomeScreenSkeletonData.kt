package com.sanjeevyadavit.magecart.domain.model

data class HomeScreenSkeletonData(
    val sliders: List<Slider>,
    val featuredCategories: List<FeaturedCategory>
) {
    companion object {
        // NOTE: Temporary function until getCmsBlock api is sorted
        fun getDummyData(): HomeScreenSkeletonData {
            val sliders = mutableListOf<Slider>()
            val featuredCategories = mutableListOf<FeaturedCategory>()

            sliders.add(Slider("Title 1", "https://plus.unsplash.com/premium_photo-1661765713449-99572ef0f0de?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"))
            sliders.add(Slider("Title 2", "https://images.unsplash.com/photo-1483985988355-763728e1935b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"))
            sliders.add(Slider("Title 3", "https://images.unsplash.com/photo-1561715276-a2d087060f1d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"))
            featuredCategories.add(FeaturedCategory(31, "Woman Sale"))
            featuredCategories.add(FeaturedCategory(15, "Man Jacket"))

            return HomeScreenSkeletonData(sliders, featuredCategories)
        }
    }
}

data class Slider(
    val title: String,
    val image: String
)

data class FeaturedCategory(
    val categoryId: Int,
    val title: String,
    var data: ProductListData? = null
)