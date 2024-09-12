package com.example.whattodo.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.whattodo.viewmodel.HomeViewModel
import com.example.whattodo.R
import com.example.whattodo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var viewBinding : FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home,container,false))
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = viewBinding.button.also {
            it.setOnClickListener {
                val navHost = parentFragment?.activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView_activity) as NavHostFragment
                val navController = navHost.navController
                navController.navigate(R.id.action_mainFragment_to_searchFragment3)
            }
        }
    }
}