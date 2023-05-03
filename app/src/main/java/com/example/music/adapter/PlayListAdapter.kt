package com.example.music.adapter


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.activity.ListSongsActivity
import com.example.music.model.Album
import com.example.music.model.PlayList
import com.squareup.picasso.Picasso

class PlayListAdapter(private val context: Context, private val listPlayList: List<PlayList>)
    : RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder>() {
        inner class PlayListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val imgButton: ImageButton = itemView.findViewById(R.id.img_btn_play_list)
            val iconArtists: ImageView = itemView.findViewById(R.id.img_icon_play_list)
            val textPlayList: TextView = itemView.findViewById(R.id.text_view_phu_pla_list)
            init {
                imgButton.setOnClickListener {
//                    Log.d("btn", position.toString())
                    val intent = Intent(context, ListSongsActivity::class.java)
                    intent.putExtra("playlist", listPlayList[position])
                    context.startActivity(intent)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.box_play_list, parent, false)
        return PlayListViewHolder(view)
    }

    override fun getItemCount(): Int = listPlayList.size

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        val playList = listPlayList[position]
        holder.textPlayList.text = playList.pls_name
        Picasso.get().load(playList.pls_icon).into(holder.iconArtists)
        Picasso.get().load(playList.pls_background).into(holder.imgButton)
    }
}
