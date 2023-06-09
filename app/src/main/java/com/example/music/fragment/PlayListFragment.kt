package com.example.music.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.activity.ListSongsActivity
import com.example.music.adapter.AdsAdapter
import com.example.music.adapter.PlayListAdapter
import com.example.music.model.Ads
import com.example.music.model.PlayList
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
 * Use the [PlayListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var listViewPlayList: ListView
    private lateinit var textTitlePlayList: TextView
    private lateinit var textViewPlayList: Button
    private lateinit var playListAdapter: PlayListAdapter
    private lateinit var playList: List<PlayList>
    private lateinit var recyclerView: RecyclerView
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_play_list, container, false)
//        listViewPlayList = view.findViewById(R.id.list_view_play_list)
        textTitlePlayList = view.findViewById(R.id.title)
        textViewPlayList = view.findViewById(R.id.btn_see)
        recyclerView = view.findViewById(R.id.recycler_view)

        getData()
        return view
    }

    private fun getData() {

        val service = DataService.getService()
        val call = service.getDataPlayList()

        call.enqueue(object : Callback<List<PlayList>> {
            override fun onResponse(call: Call<List<PlayList>>, response: Response<List<PlayList>>) {
                if (response.isSuccessful) {
                    playList = response.body()!!
                    playListAdapter = PlayListAdapter(requireContext(), playList)
                    val linearLayoutManager = LinearLayoutManager(activity)
                    linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                    recyclerView.layoutManager = linearLayoutManager
                    recyclerView.adapter = playListAdapter

                } else {
                    Log.e("API Error", response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<PlayList>>, t: Throwable) {
                Log.d("Network Error PlayList", t.toString())
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
         * @return A new instance of fragment PlayListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlayListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}