package com.sanjeevyadavit.magecart.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.sanjeevyadavit.magecart.domain.model.FeaturedCategory
import com.sanjeevyadavit.magecart.domain.model.HomeScreenSkeletonData
import com.sanjeevyadavit.magecart.domain.model.Slider

data class CmsBlockDto(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("content")
    val content: String,
    @SerializedName("creation_time")
    val creationTime: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("update_time")
    val updateTime: String
)

fun CmsBlockDto.toHomeScreenSkeletonData (): HomeScreenSkeletonData{
    val sliders = mutableListOf<Slider>()
    val featuredCategories = mutableListOf<FeaturedCategory>()

    // TODO:@SANJEEV Extract data from `content`
    // TODO:@SANJEEV Remove this dummy data
//    sliders.add(Slider("Title 1", "wysiwyg/home/home-main.jpg"))
//    sliders.add(Slider("Title 2", "wysiwyg/home/home-t-shirts.png"))
//    sliders.add(Slider("Title 3", "wysiwyg/home/yoga.jpg"))
//    featuredCategories.add(FeaturedCategory(30, "Woman Sale"))
//    featuredCategories.add(FeaturedCategory(31, "Man Sale"))

    return HomeScreenSkeletonData(sliders, featuredCategories)
}