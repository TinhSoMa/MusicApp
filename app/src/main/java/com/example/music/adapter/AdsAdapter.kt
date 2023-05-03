package com.example.music.adapter
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.PagerAdapter;
import com.example.music.R
import com.example.music.activity.ListSongsActivity
import com.example.music.model.Ads
import com.squareup.picasso.Picasso

class AdsAdapter(private val context: Context, private val listAds: List<Ads>): PagerAdapter() {
    override fun getCount(): Int = listAds.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.box_ads, container, false)
        val imgBgr = view.findViewById<ImageView>(R.id.img_bgr_ads)
        val imgView = view.findViewById<ImageView>(R.id.img_view_ads)
        val textTitle =  view.findViewById<TextView>(R.id.text_view_title)
        val textContent =  view.findViewById<TextView>(R.id.text_content)
        container.addView(view)
        textTitle.text = listAds[position].s_name
        textContent.text = listAds[position].ads_content
        Picasso.get().load(listAds[position].s_image).into(imgBgr)
        Picasso.get().load(listAds[position].ar_image).into(imgView)


        view.setOnClickListener {
//            val intent = Intent(context, ListSongsActivity::class.java)
//            intent.putExtra("songs", listAds[position])
//            startActivity(intent)
            val intent = Intent(context, ListSongsActivity::class.java)
            intent.putExtra("songs", listAds[position])
            context.startActivity(intent)

        }
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}