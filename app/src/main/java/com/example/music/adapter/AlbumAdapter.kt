package com.example.music.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.model.Album
import com.squareup.picasso.Picasso

class AlbumAdapter(private val context: Context, private val listAlbum: List<Album>)
    : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(){
    inner class AlbumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgAlbum = itemView.findViewById<ImageView>(R.id.img_album)
        val textAlbum = itemView.findViewById<TextView>(R.id.text_album)
        val textArtists = itemView.findViewById<TextView>(R.id.text_artist)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = listAlbum[position]
        holder.textAlbum.text = album.alb_name
        holder.textArtists.text = album.ar_name
        Picasso.get().load(album.alb_image).into(holder.imgAlbum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.box_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun getItemCount(): Int = listAlbum.size
}