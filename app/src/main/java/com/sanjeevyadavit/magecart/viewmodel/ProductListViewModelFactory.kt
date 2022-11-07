package com.sanjeevyadavit.magecart.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductListViewModelFactory(private val categoryId: Int,private val application: Application):
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = ProductListViewModel(categoryId, application) as T
}