package com.example.music.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.music.R
import com.example.music.adapter.AdsAdapter
import com.example.music.model.Ads
import com.example.music.service.DataService
import me.relex.circleindicator.CircleIndicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContentSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContentSearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var view: View? = null

    private lateinit var viewPager: ViewPager
    private lateinit var circleIndicator: CircleIndicator
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_content_search, container, false)
        viewPager = view.findViewById(R.id.viewPager)
        circleIndicator = view.findViewById(R.id.indicator)
//        Mapping(view)

        getData()
        return view
    }

    private fun Mapping(view: View) {
//        val view: View? = null

    }

    private fun getData() {
        val service = DataService.getService()
        val call = service.getDataAds()

        call.enqueue(object : Callback<List<Ads>> {
            override fun onResponse(call: Call<List<Ads>>, response: Response<List<Ads>>) {
                if (response.isSuccessful) {
                    val ads: List<Ads>? = response.body()
                    val adsAdapter: AdsAdapter = AdsAdapter(requireContext(), ads?: emptyList())
                    viewPager.adapter = adsAdapter
                    circleIndicator.setViewPager(viewPager)
//                    Log.d("Ads Data", ads.toString())
                    var currentItem: Int = 0
                    val delayTime: Long = 5000 // 5 seconds
                    val handler = Handler()
                    val runnable = object : Runnable {
                        override fun run() {
                            currentItem = viewPager.currentItem
                            currentItem++;
                            if (currentItem >= (viewPager.adapter as AdsAdapter).count) {
                                currentItem = 0
                            }

                            viewPager.setCurrentItem(currentItem, true)
                            handler.postDelayed(this, delayTime)
                        }
                    }

                    handler.postDelayed(runnable, delayTime)


                } else {
                    Log.e("API Error", response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<Ads>>, t: Throwable) {
                Log.e("Network Error", t.message.toString())
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ContentSearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContentSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
