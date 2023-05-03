package com.example.music.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.model.Songs
import com.squareup.picasso.Picasso


class PlaySongsAdapter(private val context: Context, private val listSong: List<Songs>)
    :RecyclerView.Adapter<PlaySongsAdapter.PlaySongsViewHolder>()
{
    inner class PlaySongsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textIndex: TextView = itemView.findViewById(R.id.text_index_song)
        val textNameSongs: TextView = itemView.findViewById(R.id.text_name_song)
        val textNameArtists: TextView = itemView.findViewById(R.id.text_name_artists)
        val icSong: ImageView = itemView.findViewById(R.id.ic_song)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaySongsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.box_item_song, parent, false)
        return PlaySongsViewHolder(view)
    }

    override fun getItemCount(): Int = listSong.size

    override fun onBindViewHolder(holder: PlaySongsViewHolder, position: Int) {
        val songs = listSong[position]
        holder.textIndex.text = (position + 1).toString()
        holder.textNameSongs.text = songs.s_name
        holder.textNameArtists.text = songs.ar_name
        Picasso.get().load(songs.s_image).into(holder.icSong)
    }

}