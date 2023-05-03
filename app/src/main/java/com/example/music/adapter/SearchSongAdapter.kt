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
import com.example.music.model.SongItem
import com.example.music.model.Songs
import com.squareup.picasso.Picasso

class SearchSongAdapter(private val context: Context, private val listSong: List<Songs>)
    : RecyclerView.Adapter<SearchSongAdapter.SearchViewHolder>()
{
        inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val textIndex: TextView = itemView.findViewById(R.id.text_index_song)
            val textNameSongs: TextView = itemView.findViewById(R.id.text_name_song)
            val textNameArtists: TextView = itemView.findViewById(R.id.text_name_artists)
            val icSong: ImageView = itemView.findViewById(R.id.ic_song)
            init {
                itemView.setOnClickListener {
                    val intent = Intent(context, PlayMusicActivity::class.java)
                    intent.putExtra("songItem", listSong[position])
                    context.startActivity(intent)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.box_item_song, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int = listSong.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val songs = listSong[position]
        holder.textIndex.text = (position + 1).toString()
        holder.textNameSongs.text = songs.s_name
        holder.textNameArtists.text = songs.ar_name
        Picasso.get().load(songs.s_image).into(holder.icSong)
    }
}