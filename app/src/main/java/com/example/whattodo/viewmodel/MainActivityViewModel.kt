package com.example.whattodo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.whattodo.service.MusicService

class MainActivityViewModel : ViewModel() {

    val navController = MutableLiveData<NavController>()

    var musicService = MutableLiveData<MusicService>(null)

    var isBinding = MutableLiveData<Boolean>(false)

}
