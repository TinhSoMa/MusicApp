package com.example.music.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import android.util.Log
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.music.R
import com.example.music.adapter.PagerPlayMusicAdapter
import com.example.music.fragment.DiskMusicFragment
import com.example.music.fragment.PlaySongsFragment
import com.example.music.model.Songs
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*

class PlayMusicActivity : AppCompatActivity() {
    private var bgr: String = ""
    private lateinit var toolbar: Toolbar
    private lateinit var textTimeSong: TextView
    private lateinit var textTotalTimeSong: TextView
    private lateinit var btbRandom: ImageButton
    private lateinit var btnImgPlay: ImageButton
    private lateinit var btnImgRepeat: ImageButton
    private lateinit var btnImgNext: ImageButton
    private lateinit var btnImgBack: ImageButton
    private lateinit var viewPager: ViewPager
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seeBar: SeekBar
    private var position: Int = 0
    private var repeat: Boolean = false
    private var checkRandom: Boolean = false
    private var nextPlay: Boolean = false
    private var index: Int = 0
    var diskMusicFragment = DiskMusicFragment()
    private var playSongsFragment = PlaySongsFragment()
    companion object {
        var listSongs: MutableList<Songs> = mutableListOf()
        var listSongItem: MutableList<Songs> = mutableListOf()
    }
    private lateinit var pagerPlayMusicAdapter: PagerPlayMusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_music)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        loadDataIntent()
        //Ánh xạ và khởi tạo
        mapping()
        if(listSongs.size>0) {
            supportActionBar?.title = listSongs[0].s_name
            PlayMp3().execute(listSongs[0].s_mp3)
            btnImgPlay.setImageResource(R.drawable.ic_pause)
            bgr = listSongs[0].s_image!!
        }
        diskMusicFragment = DiskMusicFragment()
        playSongsFragment = PlaySongsFragment()
        pagerPlayMusicAdapter = PagerPlayMusicAdapter(supportFragmentManager)
        pagerPlayMusicAdapter.addFragment(diskMusicFragment)
        pagerPlayMusicAdapter.addFragment(playSongsFragment)
        viewPager.adapter = pagerPlayMusicAdapter
        diskMusicFragment = pagerPlayMusicAdapter.getItem(0) as DiskMusicFragment
        clickEven()
    }

    private fun clickEven() {
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (listSongs.size > 0) {
                    bgr = listSongs[0].s_image!!
                    listSongs[0].s_image?.let { diskMusicFragment.loadImage(it) }

                    handler.removeCallbacks(this)
                } else {
                    handler.postDelayed(this, 300)
                }
            }
        }, 500)


        btnImgPlay.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                btnImgPlay.setImageResource(R.drawable.ic_play_30)
            } else {
                mediaPlayer.start()
                btnImgPlay.setImageResource(R.drawable.ic_pause)
            }
        }

        btnImgRepeat.setOnClickListener {

            repeat = if (!repeat) {
                //Nếu không lặp lại
                if (checkRandom) {
                    checkRandom = false
                    btnImgRepeat.setImageResource(R.drawable.ic_close)
                    btbRandom.setImageResource(R.drawable.ic_shuffle_30)
                }
                btnImgRepeat.setImageResource(R.drawable.ic_close)
                true
            } else {
                //nếu nút lặp lại hiện đang bật
                btnImgRepeat.setImageResource(R.drawable.ic_repeat_30)
                false
            }
        }

        btbRandom.setOnClickListener {
            checkRandom = if (!checkRandom) {
                //Nếu không được bật chức năng random nhưng đang bật lặp lại
                if (repeat) {
                    repeat = false
                    btnImgRepeat.setImageResource(R.drawable.ic_repeat_30)
                    btbRandom.setImageResource(R.drawable.ic_close)
                }
                btbRandom.setImageResource(R.drawable.ic_close)
                true
            } else {
                //nếu nút đang được bật
                btbRandom.setImageResource(R.drawable.ic_shuffle_30)
                false
            }
        }

        seeBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // xử lý sự kiện khi giá trị của SeekBar thay đổi

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // xử lý sự kiện khi bắt đầu kéo SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // xử lý sự kiện khi kết thúc kéo SeekBar
                mediaPlayer.seekTo(seekBar!!.progress)
            }

        })

        btnImgNext.setOnClickListener {
            if (listSongs.size > 0) {
                if (mediaPlayer.isPlaying) {
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                    }
                    mediaPlayer.stop()
//                    mediaPlayer.reset()
                    mediaPlayer.release()
//                    mediaPlayer.stop()
////                    mediaPlayer.reset()
//                    mediaPlayer.release()
                }
                if (position< listSongs.size) {
                    btnImgPlay.setImageResource(R.drawable.ic_pause)
                    position++
                    if (repeat) {
                        if (position == 0) {
                            position = listSongs.size
                        }
                        position--
                    }
                    if (checkRandom) {
                        val random = Random()
                        index = random.nextInt(listSongs.size)
                        if (index == position) {
                            position = index - 1
                        }
                        position = index
                    }
                    if (position> (listSongs.size -1)) {
                        position = 0
                    }
                    PlayMp3().execute(listSongs[position].s_mp3)
                    listSongs[position].s_image?.let { diskMusicFragment.loadImage(it) }
                    supportActionBar?.title = listSongs[position].s_name
                    updateTimeSong()
                }

            }
            btnImgNext.isClickable = false
            btnImgBack.isClickable = false

            handler.postDelayed({
                btnImgNext.isClickable = true
                btnImgBack.isClickable = true
            },2000)
        }


        btnImgBack.setOnClickListener {
            if (listSongs.size > 0) {
                if (mediaPlayer.isPlaying) {
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                    }
                    mediaPlayer.stop()
//                    mediaPlayer.reset()
                    mediaPlayer.release()
                }
                if (position< listSongs.size) {
                    btnImgPlay.setImageResource(R.drawable.ic_pause)
                    position--
                    if (position < 0 ) {
                        position = listSongs.size - 1
                    }
                    if (repeat) {
                        position++
                    }
                    if (checkRandom) {
                        val random = Random()
                        index = random.nextInt(listSongs.size)
                        if (index == position) {
                            position = index - 1
                        }
                        position = index
                    }
                    PlayMp3().execute(listSongs[position].s_mp3)
                    listSongs[position].s_image?.let { diskMusicFragment.loadImage(it) }
                    supportActionBar?.title = listSongs[position].s_name
                    updateTimeSong()
                }

            }
            btnImgNext.isClickable = false
            btnImgBack.isClickable = false

            handler.postDelayed({
                btnImgNext.isClickable = true
                btnImgBack.isClickable = true
            },2000)
        }
    }

    private fun mapping() {
        toolbar = findViewById(R.id.toolbar_music)
        textTimeSong = findViewById(R.id.text_time_music)
        textTotalTimeSong = findViewById(R.id.text_total_time_music)
        btbRandom = findViewById(R.id.btn_shuffle)
        btnImgPlay = findViewById(R.id.btn_play)
        btnImgRepeat = findViewById(R.id.btn_repeat)
        btnImgNext = findViewById(R.id.btn_sort_right)
        btnImgBack = findViewById(R.id.btn_sort_left)
        viewPager = findViewById(R.id.viewPager_music)
        seeBar = findViewById(R.id.btn_see)


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
            mediaPlayer.stop()
            listSongs.clear()
        }
        toolbar.setTitleTextColor(Color.WHITE)
    }

    private fun loadDataIntent() {
        val intent = intent
        if (listSongs.size > 0) {
            listSongs.clear()
        }
        if (intent.hasExtra("songAll")) {
            val songAll = intent.getParcelableArrayListExtra<Songs>("songAll")?.toMutableList()
            songAll?.let { listSongs.addAll(it) }
        }

        //Gửi từ SongAdapter và SearchSongAdapter
        if (intent.hasExtra("songItem")) {
            val songItem = intent.getParcelableExtra<Songs>("songItem")
            listSongs.add(songItem?: return)
        }
    }

    // Phát bài hát theo thứ tự
//    @SuppressLint("StaticFieldLeak")
//    private inner class PlayMp3: AsyncTask<String, Void, String>() {
//        @Deprecated("Deprecated in Java", ReplaceWith("string[0]"))
//        override fun doInBackground(vararg string: String): String {
//            return string[0]
//        }
//
//        override fun onPostExecute(song: String) {
//            super.onPostExecute(song)
//            mediaPlayer = MediaPlayer()
//            //Play bằng online
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
//            //Sau khi phát nhạc hoàn tất sẽ dừng bài nhạc và khởi tạo lại
//            mediaPlayer.setOnCompletionListener {
//                mediaPlayer.stop()
//                mediaPlayer.reset()
//            }
//
//            mediaPlayer.setDataSource(song)
//            mediaPlayer.prepare()
//            mediaPlayer.start()
//            timeSong()
//        }
//    }

    @SuppressLint("StaticFieldLeak")
    private inner class PlayMp3: AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg string: String) = string[0]

        override fun onPostExecute(song: String?) {
            super.onPostExecute(song)
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setOnCompletionListener {
                    stop()
                    reset()
                }
                setDataSource(song)
                prepareAsync()
                setOnPreparedListener {
                    start()
                    timeSong()
                    updateTimeSong()
                }
            }
        }
    }


    private fun timeSong() {
        val simpleDateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
        textTotalTimeSong.text = simpleDateFormat.format(mediaPlayer.duration)
        seeBar.max = mediaPlayer.duration
    }

    private fun updateTimeSong() {
        val handlerUpdate = Handler()
        handlerUpdate.postDelayed(object : Runnable{
            override fun run() {
                //Kiểm tra có đang phát bản nhạc không
                try {
                    if (mediaPlayer.isPlaying) {
                        seeBar.progress = mediaPlayer.currentPosition
                        val simpleDateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
                        textTimeSong.text = simpleDateFormat.format(mediaPlayer.currentPosition)
                        handlerUpdate.postDelayed(this, 300)
                        mediaPlayer.setOnCompletionListener {
                            nextPlay = true
                            Thread.sleep(1000)
                        }
                    }
                } catch (e: Exception) {
                    // Xử lý ngoại lệ
                    Log.d("Ngoại lệ", e.message.toString())
                }


            }

        }, 300)
        //Lắng nghe hết bài thì chuyển
        val handlerNext = Handler()
        handlerNext.postDelayed(object :Runnable{
            override fun run() {
                if(nextPlay) {
                    if (position< listSongs.size) {
                        btnImgPlay.setImageResource(R.drawable.ic_pause)
                        position++
                        if (repeat) {
                            if (position == 0) {
                                position = listSongs.size
                            }
                            position--
                        }
                        if (checkRandom) {
                            val random = Random()
                            index = random.nextInt(listSongs.size)
                            if (index == position) {
                                position = index - 1
                            }
                            position = index
                        }
                        if (position> (listSongs.size -1)) {
                            position = 0
                        }
                        PlayMp3().execute(listSongs[position].s_mp3)
                        bgr = listSongs[position].s_image!!
                        listSongs[position].s_image?.let { diskMusicFragment.loadImage(it) }
                        supportActionBar?.title = listSongs[position].s_name
                    }
                    btnImgNext.isClickable = false
                    btnImgBack.isClickable = false

                    handlerNext.postDelayed({
                        btnImgNext.isClickable = true
                        btnImgBack.isClickable = true
                    },5000)
                    nextPlay = false
                    handlerNext.removeCallbacks(this)
                } else {
                    handlerNext.postDelayed(this, 1000)
                }
            }

        },1000)
    }
}