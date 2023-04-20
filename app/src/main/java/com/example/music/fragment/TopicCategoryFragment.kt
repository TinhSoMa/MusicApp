package com.example.music.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.music.R
import com.example.music.model.Ads
import com.example.music.model.Category
import com.example.music.model.Topic
import com.example.music.model.TopicCategory
import com.example.music.service.DataService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TopicCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopicCategoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var horizontalScrollView: HorizontalScrollView
    private lateinit var textShow: TextView
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
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_topic_category, container, false)
        // Inflate the layout for this fragment
        horizontalScrollView = view.findViewById(R.id.horizontal)
        textShow = view.findViewById(R.id.text_show)
        getData()
        return view
    }

    private fun getData() {
        val service = DataService.getService()
        val call = service.getDataTopicCategory()

        call.enqueue(object : Callback<TopicCategory>{
            override fun onResponse(call: Call<TopicCategory>, response: Response<TopicCategory>) {
                val listTopicCategory: TopicCategory? = response.body()


                val listTopic = ArrayList<Topic>()
                listTopic.addAll(listTopicCategory!!.topic_data)

                val listCategory = ArrayList<Category>()
                listCategory.addAll(listTopicCategory.category_data)

                val linearLayout = LinearLayout(activity)
                linearLayout.orientation = LinearLayout.HORIZONTAL

                val layoutParams = LinearLayout.LayoutParams(580, 250)
                layoutParams.setMargins(10, 20, 10 ,30)

                for (i in listTopic.indices) {
                    val cardView = CardView(activity!!)
                    cardView.radius = 10F
                    val imgView = ImageView(activity)
                    imgView.scaleType = ImageView.ScaleType.FIT_XY
                    Picasso.get().load(listTopic[i].tp_image).into(imgView)
                    cardView.layoutParams = layoutParams
                    cardView.addView(imgView)
                    linearLayout.addView(cardView)
                }
                for (i in listCategory.indices) {
                    val cardView = CardView(activity!!)
                    cardView.radius = 10F
                    val imgView = ImageView(activity)
                    imgView.scaleType = ImageView.ScaleType.FIT_XY
                    Picasso.get().load(listCategory[i].ctgr_image).into(imgView)
                    cardView.layoutParams = layoutParams
                    cardView.addView(imgView)
                    linearLayout.addView(cardView)
                }
                horizontalScrollView.addView(linearLayout)
            }

            override fun onFailure(call: Call<TopicCategory>, t: Throwable) {
                Log.d("NetworkTopic", t.toString())

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
         * @return A new instance of fragment TopicCategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TopicCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}