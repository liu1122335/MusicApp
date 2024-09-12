package com.example.whattodo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class MainActivityViewModel : ViewModel() {

    val navController = MutableLiveData<NavController>()

}
