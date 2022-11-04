package com.sanjeevyadavit.magecart.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sanjeevyadavit.magecart.domain.use_case.GetProductListUseCase
import javax.inject.Inject

class ProductListViewModelFactory constructor(
    private val categoryId: Int,
    private val getProductListUseCase: GetProductListUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        ProductListViewModel(categoryId, getProductListUseCase) as T
}