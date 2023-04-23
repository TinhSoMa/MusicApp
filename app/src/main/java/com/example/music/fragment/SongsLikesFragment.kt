package com.example.music.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.adapter.SongLikeAdapter
import com.example.music.model.Songs
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
 * Use the [SongsLikesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SongsLikesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var view: View
    private lateinit var textShow: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var songLikeAdapter: SongLikeAdapter
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
    ): View {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_songs_likes, container, false)
        textShow = view.findViewById(R.id.text_show)
        recyclerView = view.findViewById(R.id.recycler_view)
        getData()
        return view
    }

    private fun getData() {
        val service = DataService.getService()
        val call = service.getDataSongs()

        call.enqueue(object : Callback<List<Songs>> {
            override fun onResponse(call: Call<List<Songs>>, response: Response<List<Songs>>) {
                val songs: List<Songs>? = response.body()
                songLikeAdapter = SongLikeAdapter(requireContext(), songs!!)
                val linearLayoutManager = LinearLayoutManager(activity)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = songLikeAdapter

            }

            override fun onFailure(call: Call<List<Songs>>, t: Throwable) {
                Log.d("NetworkSongs", t.toString())

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
         * @return A new instance of fragment SongsLikesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SongsLikesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}