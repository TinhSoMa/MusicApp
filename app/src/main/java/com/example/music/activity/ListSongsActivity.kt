package com.example.music.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.adapter.SongAdapter
import com.example.music.model.*
import com.example.music.service.DataService
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.URL
import java.util.ArrayList

class ListSongsActivity : AppCompatActivity() {
    private var ads: Ads? = null
    private var songs: Songs? = null
    private var playList: PlayList? = null
    private lateinit var btnShow: Button
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var listSongs: List<Songs>
    private lateinit var songAdapter: SongAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_songs)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        coordinatorLayout = findViewById(R.id.coordinator_layout)
        collapsingToolbarLayout = findViewById(R.id.collapsing)
        toolbar = findViewById(R.id.toolbar)
//        imgIc = findViewById(R.id.img_ic)
        recyclerView = findViewById(R.id.recycler_view)
        floatingActionButton = findViewById(R.id.floating)
        btnShow = findViewById(R.id.btn_show_song)

        //Lấy dữ liệu từ các Activity hoặc Fragment
        loadDataIntent()
        //Cài đặt các nút của toolbar(nút quay lại)
        setViewBGR()

        //Kiểm tra đối playList có null hay không
        playList?.run {
            getDataPlayList(playList!!.pls_id)
            loadBGR(playList!!.pls_background, playList!!.pls_name)
//            Log.d("ac_pl", playList!!.pls_id)
        }
        ads?.run {
            getDataAds(ads!!.ads_id)
            loadBGR(ads!!.ar_image, ads!!.ar_name)
//            Log.d("ktra", ads!!.s_image)
        }


        btnShow.setOnClickListener {
            val intent = Intent(this, PlayMusicActivity::class.java)
            intent.putParcelableArrayListExtra("songAll", ArrayList(listSongs))
            this.startActivity(intent)
        }
    }

    private fun getDataPlayList(pls_id: String) {
        val service = DataService.getService()
        val call = service.getDataPlayListSong(pls_id)

        call.enqueue(object :Callback<List<Songs>> {
            override fun onResponse(call: Call<List<Songs>>, response: Response<List<Songs>>) {
                listSongs = response.body()!!
                songAdapter = SongAdapter(this@ListSongsActivity, listSongs)
                recyclerView.layoutManager = LinearLayoutManager(this@ListSongsActivity)
                recyclerView.adapter = songAdapter
            }

            override fun onFailure(call: Call<List<Songs>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getDataAds(ads_id: String) {
        val service = DataService.getService()
        val call = service.getDataListAds(ads_id)
        call.enqueue(object : Callback<List<Songs>> {
            override fun onResponse(call: Call<List<Songs>>, response: Response<List<Songs>>) {
                listSongs = response.body()!!
                songAdapter = SongAdapter(this@ListSongsActivity, listSongs)
                recyclerView.layoutManager = LinearLayoutManager(this@ListSongsActivity)
                recyclerView.adapter = songAdapter
            }

            override fun onFailure(call: Call<List<Songs>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun loadDataIntent() {
        val intent = intent

        if (intent.hasExtra("songs")) {
            ads = intent.getSerializableExtra("songs") as Ads
        }

        //Nhận dữ liệu từ PlayListAdapter
        if (intent.hasExtra("playlist")) {
            playList = intent.getSerializableExtra("playlist") as PlayList
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class DownloadImageTask : AsyncTask<String, Void, Bitmap>() {

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg urls: String): Bitmap? {
            val url = urls[0]

            var bmp: Bitmap? = null
            try {
                val inputStream = URL(url).openStream()
                bmp = BitmapFactory.decodeStream(inputStream)
            } catch (e: IOException) {
                Log.d("Error", e.message.toString())
                e.printStackTrace()
            }
            return bmp
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Bitmap?) {
            if (result != null) {
                val bitmapDrawable = BitmapDrawable(resources, result)
//                imgIc.setImageBitmap(result)
                collapsingToolbarLayout.background = bitmapDrawable
            }
        }
    }



    private fun setViewBGR() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        toolbar.setNavigationIcon()
        toolbar.setNavigationOnClickListener {
            finish()
        }
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE)
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE)
    }

    private fun loadBGR(imgBGR: String, nameArt: String) {
        DownloadImageTask().execute(imgBGR)
        collapsingToolbarLayout.title = nameArt
//        Picasso.get().load(imgBGR).into(imgIc)

    }
}