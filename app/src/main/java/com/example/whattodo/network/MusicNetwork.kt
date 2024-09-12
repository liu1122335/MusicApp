package com.example.whattodo.network

import android.util.Log
import android.widget.Toast
import com.example.whattodo.MyApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object MusicNetwork {

    private val musicService  = ServiceCreator.create<MusicService>()

    suspend fun searchMusic1(musicTitle : String , typeChannel : String) = musicService.getMusic1(musicTitle, typeChannel).await()
    suspend fun searchMusic2(musicTitle : String , typeChannel : String) = musicService.getMusic2(musicTitle, typeChannel).await()

    private suspend fun <T> Call<T>.await() : T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(p0: Call<T>, p1: Response<T>) {
                    val body = p1.body()
                    if (body!=null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(p0: Call<T>, p1: Throwable) {
                    Log.d("timeout","timeout")
                    //continuation.resumeWithException(p1)
                    Toast.makeText(MyApplication.context,"网络请求超时", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

}