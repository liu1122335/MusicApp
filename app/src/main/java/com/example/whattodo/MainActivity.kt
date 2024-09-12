package com.example.whattodo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.whattodo.viewmodel.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val viewmodel = MainActivityViewModel()

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



        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView_activity) as NavHostFragment

        val navController = navHost.navController

        viewmodel.navController.value = navHost.navController

        onBackPressedDispatcher.addCallback {
            if (navController.currentDestination?.id == R.id.mainFragment){
                finish()
            }else{
                navController.popBackStack()
            }
        }
    }

}