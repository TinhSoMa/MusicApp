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

class SongLikeAdapter(private val context: Context, private val listSongs: List<Songs>)
    : RecyclerView.Adapter<SongLikeAdapter.SongsViewHolder>(){
    inner class SongsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgSongs: ImageView = itemView.findViewById(R.id.img_song_like)
        val textNameSongs: TextView = itemView.findViewById(R.id.text_title)
        val textNameArtists: TextView = itemView.findViewById(R.id.text_artist)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.box_songs_like, parent, false)
        return SongsViewHolder(view)
    }

    override fun getItemCount(): Int = listSongs.size

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        val songs = listSongs[position]
        holder.textNameSongs.text = songs.s_name
        holder.textNameArtists.text = songs.ar_name
        Picasso.get().load(songs.s_image).into(holder.imgSongs)
    }
}