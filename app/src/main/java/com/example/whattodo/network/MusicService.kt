package com.example.whattodo.network

import com.example.whattodo.model.MusicResponse1
import com.example.whattodo.model.MusicResponse2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {

    @GET("api.php")
    fun getMusic1(@Query("music") musicTitle : String ,@Query("type") typeChannel : String):Call<MusicResponse1>

    @GET("api.php")
    fun getMusic2(@Query("music") musicTitle : String ,@Query("type") typeChannel : String):Call<MusicResponse2>

}