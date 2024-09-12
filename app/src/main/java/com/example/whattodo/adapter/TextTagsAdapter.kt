package com.example.whattodo.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.moxun.tagcloudlib.view.TagsAdapter


class TextTagsAdapter(data: List<String>) : TagsAdapter() {
    private val dataSet: MutableCollection<in String?> = ArrayList()

    init {
        dataSet.clear()
        dataSet.addAll(data)
    }

    override fun getCount(): Int {
        return dataSet.size
    }

    override fun getView(context: Context, position: Int, parent: ViewGroup): View {
        val tv = TextView(context)
        tv.text = "No.$position"
        tv.gravity = Gravity.CENTER
        tv.setOnClickListener {
            Log.e("Click", "Tag $position clicked.")
            Toast.makeText(context, "Tag $position clicked", Toast.LENGTH_SHORT).show()
        }
        tv.setTextColor(Color.WHITE)
        return tv
    }

    override fun getItem(position: Int): Any? {
        return dataSet.toList()[position]
    }

    override fun getPopularity(position: Int): Int {
        return position % 7
    }

    override fun onThemeColorChanged(view: View?, themeColor: Int) {
        view?.setBackgroundColor(themeColor)
    }
}