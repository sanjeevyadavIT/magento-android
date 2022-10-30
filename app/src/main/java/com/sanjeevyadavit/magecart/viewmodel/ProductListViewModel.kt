package com.sanjeevyadavit.magecart.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.model.CategoryTree
import com.sanjeevyadavit.magecart.model.product.ProductData
import com.sanjeevyadavit.magecart.model.product.Products
import com.sanjeevyadavit.magecart.service.ApiInterface
import com.sanjeevyadavit.magecart.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val PAGE_SIZE = 10

class ProductListViewModel(private val categoryId: Int, app: Application): AndroidViewModel(app){

    private val facade: ApiInterface = RetrofitClient.getInstance().create(ApiInterface::class.java)

    private val _products = mutableStateOf<List<ProductData>>(listOf())
    val products: State<List<ProductData>>
        get() = _products

    private var _loadMore = mutableStateOf(false)
    val loadMore: State<Boolean>
        get() = _loadMore

    private var totalCount: Int = 0
    private var productListScrollPosition = 0

    init {
        getProducts()
    }

    fun getProducts(){
        val currentPage = (_products.value.size / PAGE_SIZE) + 1;
        if(currentPage > 1) {
            _loadMore.value = true
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // TODO: this logic should be in Repository
                val response = facade.getProducts(filter0Field = "category_id", filter0Value = categoryId, currentPage = currentPage, pageSize = PAGE_SIZE)
                withContext(Dispatchers.Main) {
                    if(currentPage == 1) {
                        _products.value = response.items
                        totalCount = response.totalCount
                    } else {
                        val newList = mutableListOf<ProductData>()
                        newList.addAll(_products.value)
                        newList.addAll(response.items)
                        _products.value = newList
                    }
                    showToast("Products fetched successfully")
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("MYTAG", it) }
                withContext(Dispatchers.Main) {
                    showToast("Error: ${e.message}")
                }
            } finally {
                _loadMore.value = false
            }
        }
    }

    fun fetchNextPage(){
        // prevent duplicate event due to recompose happening to quickly
        if((productListScrollPosition + 1) < (totalCount) && !_loadMore.value){
            getProducts()
        }
    }

    fun onChangeProductListScrollPosition(position: Int){
        productListScrollPosition = position
    }

    fun getTotalCount(): Int  = totalCount

    private fun showToast(msg: String) =  Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()

}