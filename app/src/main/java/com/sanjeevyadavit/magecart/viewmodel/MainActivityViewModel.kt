package com.sanjeevyadavit.magecart.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    val isLoggedIn: MutableLiveData<Boolean> = MutableLiveData(false)

}