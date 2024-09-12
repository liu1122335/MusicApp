package com.example.whattodo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattodo.model.Music
import com.example.whattodo.model.Music1
import com.example.whattodo.model.Music2
import com.example.whattodo.network.MusicNetwork
import kotlinx.coroutines.launch

class SearchResultViewModel : ViewModel() {
    var musicLiveData = MutableLiveData<List<Music>?>()

    var currentChannel = MutableLiveData<String>("netease")

    var isBinding = MutableLiveData<Boolean>(false)

    var currentMusic = MutableLiveData<Music>()

    fun searchMusic(
        musicTitle : String,
        musicChannel : String
    ){
        when(musicChannel){
            "netease" -> viewModelScope.launch {
                val response = MusicNetwork.searchMusic1(musicTitle,musicChannel)
                if (response.code == 200){
                    val musicList = ArrayList<Music>()
                    response.data.forEach {
                        musicList.add(Music(it.title,it.author,it.lrc,it.url,it.pic))
                    }
                    musicLiveData.value = musicList
                }
            }
            "kugou" -> viewModelScope.launch {
                val response = MusicNetwork.searchMusic2(musicTitle,musicChannel)
                if (response.code == 200){
                    val musicList = ArrayList<Music>()
                    response.data.forEach {
                        musicList.add(Music(title = it.title, author = it.author, lrc = it.lrc, url = it.url, pic = it.pic))
                    }
                    musicLiveData.value = musicList
                }
            }
        }
    }
}