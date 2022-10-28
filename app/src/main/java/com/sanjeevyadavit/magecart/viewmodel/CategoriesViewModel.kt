package com.sanjeevyadavit.magecart.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.model.CategoryTree
import com.sanjeevyadavit.magecart.service.ApiInterface
import com.sanjeevyadavit.magecart.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriesViewModel(app: Application): AndroidViewModel(app) {

    private val facade: ApiInterface = RetrofitClient.getInstance().create(ApiInterface::class.java)

    private val _categoryTree: MutableState<List<CategoryTree>> = mutableStateOf(listOf())
    val categoryTree: State<List<CategoryTree>>
        get() = _categoryTree

    init {
        getCategoryTree()
    }

    fun getCategoryTree(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // TODO: this logic should be in Repository
                val response = facade.getCategoryTree()
                withContext(Dispatchers.Main) {
                    _categoryTree.value = response.childrenData
                    showToast("Categories fetched successfully")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showToast("Error: ${e.message}")
                }
            }
        }
    }

    private fun showToast(msg: String) =  Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()
}