package com.example.whattodo

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.whattodo.MyApplication.Companion.context
import com.example.whattodo.service.MusicService
import com.example.whattodo.viewmodel.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    //val viewmodel : MainActivityViewModel by viewModels()
    var musicService: MusicService? = null
    lateinit var viewModel : MainActivityViewModel

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/


        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val intent = Intent(context, MusicService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView_activity) as NavHostFragment

        val navController = navHost.navController

        viewModel.navController.value = navHost.navController

        onBackPressedDispatcher.addCallback {
            if (navController.currentDestination?.id == R.id.mainFragment){
                finish()
            }else{
                navController.popBackStack()
            }
        }
    }

    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder: MusicService.MusicBinder = service as MusicService.MusicBinder
            musicService = binder.getService()
            Log.d("onServiceConnected","true")
            viewModel.isBinding.value = true
            viewModel.musicService.value = musicService
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.d("onServiceConnected","false")
            viewModel.isBinding.value = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (viewModel.isBinding.value!!){
            unbindService(serviceConnection)
            viewModel.isBinding.value = false
        }
    }

}