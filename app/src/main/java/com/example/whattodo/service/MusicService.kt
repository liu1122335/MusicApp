package com.example.whattodo.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.whattodo.MainActivity
import com.example.whattodo.R
import retrofit2.http.Url
import java.io.IOException

class MusicService : Service() {

    private val binder = MusicBinder()
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false
    private var currentMusicUrl = ""

    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setOnCompletionListener {
            stopSelf()
        }
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, createNotification())
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }


    fun playMusic(musicUrl: String) {
        Log.d("Service", "play")

        if (isPlaying) {
            if (musicUrl == currentMusicUrl) {
                mediaPlayer?.pause()
                isPlaying = false
            } else {
                mediaPlayer?.reset()
                isPlaying = false
                playMusic(musicUrl) // 递归调用以重新播放新音乐
            }
            return
        }

        isPlaying = true
        if (currentMusicUrl != musicUrl) {
            try {
                mediaPlayer?.reset()
                currentMusicUrl = musicUrl
                mediaPlayer?.setDataSource(musicUrl)
                mediaPlayer?.prepareAsync()
                mediaPlayer?.setOnPreparedListener { mp ->
                    mp?.start()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                // 增加错误处理机制，例如通知用户
                Log.e("Service", "Failed to play music: ${e.message}")
                isPlaying = false
            }
        } else {
            mediaPlayer?.start()
        }
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "music_service_channel",
                "Music Service Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
            PendingIntent.FLAG_MUTABLE)

        return NotificationCompat.Builder(this, "music_service_channel")
            .setContentTitle("Music Player")
            .setContentText("Playing music in the background")
            .setSmallIcon(R.drawable.ic_home)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }
}
