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
import com.sanjeevyadavit.magecart.model.product.Products
import com.sanjeevyadavit.magecart.service.ApiInterface
import com.sanjeevyadavit.magecart.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductListViewModel(private val categoryId: Int, app: Application): AndroidViewModel(app){
    private val facade: ApiInterface = RetrofitClient.getInstance().create(ApiInterface::class.java)

    private val _products = mutableStateOf<Products?>(null)
    val products: State<Products?>
        get() = _products

    init {
        getProducts()
    }

    fun getProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // TODO: this logic should be in Repository
                val response = facade.getProducts(filter0Field = "category_id", filter0Value = categoryId)
                withContext(Dispatchers.Main) {
                    _products.value = response
                    showToast("Products fetched successfully")
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("MYTAG", it) }
                withContext(Dispatchers.Main) {
                    showToast("Error: ${e.message}")
                }
            }
        }
    }

    private fun showToast(msg: String) =  Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()
}