package com.example.whattodo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.whattodo.R
import com.example.whattodo.model.Music
import com.example.whattodo.model.Music1

class MusicItemAdapter(
    val musicList: List<Music>,
    val onItemClickListener : (ViewHolder)->Unit
    ) :
    RecyclerView.Adapter<MusicItemAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val musicImage: ImageView = view.findViewById(R.id.music_item_image)
        val musicTitleText: TextView = view.findViewById(R.id.music_item_title)
        val musicAuthorText: TextView = view.findViewById(R.id.music_item_author)
        val musicDownloadImage: ImageView = view.findViewById(R.id.music_item_download)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.result_music_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val music = musicList[position]
        holder.musicImage.setImageURI(music.pic.toUri())
        holder.musicTitleText.setText(music.title)
        holder.musicAuthorText.setText(music.author)
        holder.musicDownloadImage.setImageResource(R.drawable.ic_download)
        holder.itemView.setOnClickListener {
            onItemClickListener(holder)
        }

    }

    override fun getItemCount(): Int = musicList.size

}