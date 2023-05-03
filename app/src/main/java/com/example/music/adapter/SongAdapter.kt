package com.example.music.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.activity.ListSongsActivity
import com.example.music.activity.PlayMusicActivity
import com.example.music.model.Songs
import java.util.ArrayList

class SongAdapter(private val context: Context, private val listSongs: List<Songs>)
    : RecyclerView.Adapter<SongAdapter.SongListViewHodel>() {
        inner class SongListViewHodel(itemView: View): RecyclerView.ViewHolder(itemView) {
            val textIndex: TextView = itemView.findViewById(R.id.text_index)
            val textSongName: TextView = itemView.findViewById(R.id.text_s_name)
            val textNameArtists: TextView = itemView.findViewById(R.id.text_ar_name)
            val textLove: ImageView = itemView.findViewById(R.id.img_love)
            init {
                itemView.setOnClickListener {
                    //Gửi từ vị trí click các item của playlist
                    val intent = Intent(context, PlayMusicActivity::class.java)
                    intent.putExtra("songItem", listSongs[position])
                    context.startActivity(intent)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListViewHodel {
        val view = LayoutInflater.from(context).inflate(R.layout.box_list_ads, parent, false)
        return SongListViewHodel(view)
    }

    override fun getItemCount(): Int = listSongs.size

    override fun onBindViewHolder(holder: SongListViewHodel, position: Int) {
        val list = listSongs[position]
        holder.textIndex.text = (position + 1).toString()
        holder.textSongName.text = list.s_name
        holder.textNameArtists.text = list.ar_name
    }
}