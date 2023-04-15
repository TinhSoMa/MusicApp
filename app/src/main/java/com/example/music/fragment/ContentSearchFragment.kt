package com.example.music.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.music.R
import com.example.music.model.Ads
import com.example.music.service.APIService
import com.example.music.service.DataService
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
        //getData()
        return inflater.inflate(R.layout.fragment_content_search, container, false)
    }

    private fun getData() {
        val dataService: DataService = APIService.getService()
        val callback: Call<List<Ads>> = dataService.getDataAds()
        callback.enqueue(object : Callback<List<Ads>> {
            override fun onResponse(call: Call<List<Ads>>, response: Response<List<Ads>>) {
                if (response.isSuccessful) {
                    val ads: List<Ads>? = response.body()
                    // Xử lý dữ liệu ở đây
                } else {
                    // Yêu cầu thất bại hoặc không có dữ liệu trả về
                }

            }

            override fun onFailure(call: Call<List<Ads>>, t: Throwable) {
                TODO("Not yet implemented")
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