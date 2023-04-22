package com.example.music.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter;
import com.example.music.R
import com.example.music.model.Ads
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class AdsAdapter(private val context: Context, private val listAds: List<Ads>): PagerAdapter() {
    override fun getCount(): Int = listAds.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.box_ads, container, false)
        val imgBgr = view.findViewById<ImageView>(R.id.img_bgr_ads)
        val imgView = view.findViewById<ImageView>(R.id.img_view_ads)
        val textTitle =  view.findViewById<TextView>(R.id.text_view_title)
        val textContent =  view.findViewById<TextView>(R.id.text_content)
        container.addView(view)


//        Picasso.with(context).load("https://mp3-application.000webhostapp.com/Image/img_songs/s_nguoiyeugiandon.jpg").into(imgBgr)
//        Picasso.get().load("https://mp3-application.000webhostapp.com/Image/img_songs/s_nguoiyeugiandon.jpg")
//            .placeholder(R.drawable.bgr_error)
//            .error(R.drawable.bgr_error)
//            .into(imgBgr)
        Picasso.get().load(listAds[position].s_image).into(imgBgr, object : Callback {
            override fun onSuccess() {
                Log.d("TAG1", "Thanh cong")
            }

            override fun onError(e: Exception?) {
                Log.d("TAG2", e.toString())
            }
        })
        Picasso.get().load(listAds[position].ar_image).into(imgView, object : Callback {
            override fun onSuccess() {
                Log.d("TAG1", "Thanh cong")
            }

            override fun onError(e: Exception?) {
                Log.d("TAG2", e.toString())
            }
        })

//        Picasso.get().load(listAds[position].ar_image).into(imgView)
        textTitle.text = listAds[position].s_name
        textContent.text = listAds[position].ads_content
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}