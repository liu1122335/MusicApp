package com.example.whattodo.view

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whattodo.MainActivity
import com.example.whattodo.R
import com.example.whattodo.adapter.MusicItemAdapter
import com.example.whattodo.databinding.FragmentSearchResultBinding
import com.example.whattodo.service.MusicService
import com.example.whattodo.viewmodel.MainActivityViewModel
import com.example.whattodo.viewmodel.SearchResultViewModel
import com.google.android.material.tabs.TabLayout


class SearchResultFragment : Fragment() {

    companion object {
        fun newInstance() = SearchResultFragment()
    }

    private val viewModel: SearchResultViewModel by viewModels()
    private lateinit var viewBinding: FragmentSearchResultBinding
    private var musicService: MusicService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SearchResultFragment","onCreate")
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSearchResultBinding.bind(inflater.inflate(R.layout.fragment_search_result, container, false))
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val mainActivityViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        musicService = mainActivityViewModel.musicService.value

        val musicTitle = arguments?.getString("musicTitle")
        val recyclerView =  viewBinding.resultRecyclerview
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        viewBinding.resultChannel.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { position ->
                    Log.d("tab", position.toString())
                    viewModel.currentChannel.value = when (position) {
                        0 -> "netease"
                        1 -> "kugou"
                        else -> null
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // 不需要处理
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 不需要处理
            }
        })

        viewModel.currentChannel.observe(viewLifecycleOwner) { channel ->
            Log.d("channel", "change")
            musicTitle?.let { title ->
                viewModel.searchMusic(title, channel)
            } ?: Log.e("SearchResultFragment", "musicTitle is null")
        }


        viewModel.musicLiveData.observe(viewLifecycleOwner) { musicList ->
            Log.d("musicLivedata", "change")
            val adapter = MusicItemAdapter(
                musicList = musicList!!,
                onItemClickListener = { viewHolder ->
                    val position = viewHolder.adapterPosition
                    Log.d("position", position.toString())
                    val music = musicList.getOrNull(position) ?: return@MusicItemAdapter

                    musicService?.let { service ->
                        try {
                            service.playMusic(music.url)
                        } catch (e: Exception) {
                            Log.e("MusicService", "Failed to play music", e)
                        }
                    } ?: run {
                        Log.e("MusicService", "MusicService is not bound")
                    }

                }
            )
            recyclerView.adapter = adapter
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("SearchResultFragment","onDestroy")
    }

}