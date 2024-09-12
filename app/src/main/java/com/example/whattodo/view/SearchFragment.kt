package com.example.whattodo.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.whattodo.R
import com.example.whattodo.databinding.FragmentSearchBinding
import com.example.whattodo.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var viewBinding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSearchBinding.bind(inflater.inflate(R.layout.fragment_search, container, false))
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHost = childFragmentManager.findFragmentById(R.id.fragmentContainerView_search) as NavHostFragment
        val navController = navHost.navController
        val inputQuery = viewBinding.editQuery.text

        viewBinding.editQuery.addTextChangedListener {
            if (it?.isEmpty() == true){
                navController.popBackStack()
            }
        }

        viewBinding.buttonQuery.setOnClickListener {
            if (inputQuery.isEmpty()){
                Toast.makeText(context,"请输入歌名",Toast.LENGTH_SHORT).show()
            }else{
                Log.d("queryButton",navController.currentDestination?.label.toString())
                if (navController.currentDestination?.id == R.id.searchTempFragment){
                    navController.navigate(R.id.action_searchTempFragment_to_searchResultFragment,Bundle().apply { putString("musicTitle",inputQuery.toString()) })
                }else{
                    navController.navigate(R.id.action_searchResultFragment_self,Bundle().apply { putString("musicTitle",inputQuery.toString()) })
                }
            }
        }

    }
}