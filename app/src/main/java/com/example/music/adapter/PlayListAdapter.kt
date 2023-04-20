package com.example.music.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.music.R
import com.example.music.model.PlayList
import com.squareup.picasso.Picasso

class PlayListAdapter(context: Context, resource: Int, objects: List<PlayList>)
    : ArrayAdapter<PlayList>(context, resource, objects) {
    private class ViewHolder {
        var textNameSong: TextView? = null
        var imgBtn: ImageButton? =null
        var imgIcon: ImageView? =null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder?
        var convertView = convertView

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.box_play_list, parent, false)
            viewHolder = ViewHolder()
            viewHolder.textNameSong = convertView.findViewById(R.id.text_view_phu_pla_list)
            viewHolder.imgBtn = convertView.findViewById(R.id.img_btn_play_list)
            viewHolder.imgIcon = convertView.findViewById(R.id.img_icon_play_list)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        val playList = getItem(position)
        Picasso.get().load(playList?.pls_background).into(viewHolder.imgBtn)
        Picasso.get().load(playList?.pls_icon).into(viewHolder.imgIcon)
        viewHolder.textNameSong?.text = playList?.pls_name
        return convertView!!
    }
}
