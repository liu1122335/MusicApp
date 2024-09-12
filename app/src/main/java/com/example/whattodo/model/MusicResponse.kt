package com.example.whattodo.model

data class MusicResponse1(
    val data : List<Music1>,
    val code : Int,
    val error : String
)

data class MusicResponse2(
    val data : List<Music2>,
    val code : Int,
    val error : String
)

data class Music1(
    val type : String,
    val link : String,
    val songid : Long,
    val title : String,
    val author : String,
    val lrc : String,
    val url : String,
    val pic : String
)

data class Music2(
    val type : String,
    val link : String,
    val songid : String,
    val title : String,
    val author : String,
    val lrc : String,
    val url : String,
    val pic : String
)

data class Music(
    val title : String,
    val author : String,
    val lrc : String,
    val url : String,
    val pic : String
)