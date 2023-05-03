package com.example.music.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.music.R
import com.example.music.activity.PlayMusicActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import me.relex.circleindicator.CircleIndicator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiskMusicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiskMusicFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var circleImageView: CircleImageView
    private lateinit var objectAnimator: ObjectAnimator
    private lateinit var view: View
    private lateinit var bgr: String
    private val ss: String = "ss"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_disk_music, container, false)
        circleImageView = view.findViewById(R.id.circle_center)
//        objectAnimator = ObjectAnimator.ofFloat(circleImageView, "rotation", 0f, 360f)
//        objectAnimator.duration = 10000
//        objectAnimator.repeatCount = ValueAnimator.INFINITE
//        objectAnimator.repeatMode = ValueAnimator.RESTART
//        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator = ObjectAnimator.ofFloat(circleImageView, "rotation", 0f, 360f)
        objectAnimator.duration = 10000
        objectAnimator.repeatCount = ValueAnimator.INFINITE
        objectAnimator.repeatMode = ValueAnimator.RESTART
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.start()
//        loadCircleImageView()
        return view
    }

    fun loadImage(hinhanh: String) {
        bgr = hinhanh
        if (::circleImageView.isInitialized) {
            Picasso.get().load(bgr).into(circleImageView)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DiskMusicFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiskMusicFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}