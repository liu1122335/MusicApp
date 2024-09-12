package com.example.whattodo.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whattodo.R
import com.example.whattodo.databinding.FragmentSearchTempBinding
import com.example.whattodo.viewmodel.SearchTempViewModel

class SearchTempFragment : Fragment() {

    companion object {
        fun newInstance() = SearchTempFragment()
    }

    private val viewModel: SearchTempViewModel by viewModels()
    private lateinit var viewBinding: FragmentSearchTempBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        viewBinding = FragmentSearchTempBinding.bind(inflater.inflate(R.layout.fragment_search_temp, container, false))
        return viewBinding.root
    }
}