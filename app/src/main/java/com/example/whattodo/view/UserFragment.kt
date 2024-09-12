package com.example.whattodo.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whattodo.R
import com.example.whattodo.adapter.TextTagsAdapter
import com.example.whattodo.databinding.FragmentUserBinding
import com.example.whattodo.viewmodel.UserViewModel
import com.moxun.tagcloudlib.view.TagCloudView

class UserFragment : Fragment() {

    private lateinit var tagCloudView : TagCloudView
    private lateinit var textCloudView: TextTagsAdapter
    private lateinit var viewbinding : FragmentUserBinding

    companion object {
        fun newInstance() = UserFragment()
    }

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewbinding = FragmentUserBinding.bind(inflater.inflate(R.layout.fragment_user,container,false))
        return viewbinding.root
    }

    override fun onStart() {
        super.onStart()
        tagCloudView = viewbinding.tagCloud
        textCloudView = TextTagsAdapter(listOf("1","2","3","4","5","6","7","8","10","11","12","13","14","15","16","17","18","19","20"))

        tagCloudView.setAdapter(textCloudView)
    }
}