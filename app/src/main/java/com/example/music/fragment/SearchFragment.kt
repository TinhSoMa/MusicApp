package com.example.music.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.adapter.SearchSongAdapter
import com.example.music.model.PlayList
import com.example.music.model.SongItem
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
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var view: View
    private lateinit var toolbar: Toolbar
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var textNoSong: TextView
//    private lateinit var listSongs: List<Songs>
    private lateinit var searchSongAdapter: SearchSongAdapter

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
    ): View {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        textNoSong = view.findViewById(R.id.no_song)

        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = view.findViewById(R.id.search_view)
        toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = ""
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {
                    val service = DataService.getService()
                    val call = service.getDataSearchSong(query)

                    call.enqueue(object : Callback<List<Songs>> {
                        override fun onResponse(
                            call: Call<List<Songs>>,
                            response: Response<List<Songs>>
                        ) {
                            val listSongs: List<Songs>? = response.body()
                            if (listSongs != null) {
                                searchSongAdapter = SearchSongAdapter(requireContext(), listSongs)
                                val linearLayoutManager = LinearLayoutManager(activity)
                                recyclerView.layoutManager = linearLayoutManager
                                recyclerView.adapter = searchSongAdapter
                                textNoSong.visibility = View.GONE
                                recyclerView.visibility = View.VISIBLE
                            } else {
                                textNoSong.visibility = View.VISIBLE
                                recyclerView.visibility = View.GONE
                            }
                        }

                        override fun onFailure(call: Call<List<Songs>>, t: Throwable) {
                            Log.d("SSS", t.toString())
                        }

                    })
                }

//                searchSong(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                searchSong(newText.toString())
                return true
            }

        })
    }

//    private fun searchSong(query: String) {
//        val service = DataService.getService()
//        val call = service.getDataSearchSong(query)
//
//        call.enqueue(object : Callback<List<Songs>> {
//            override fun onResponse(call: Call<List<Songs>>, response: Response<List<Songs>>) {
//                listSongs = response.body()!!
//                if (listSongs.isNotEmpty()) {
//                    searchSongAdapter = SearchSongAdapter(requireContext(), listSongs)
//                    val linearLayoutManager = LinearLayoutManager(activity)
//                    recyclerView.layoutManager = linearLayoutManager
//                    recyclerView.adapter = searchSongAdapter
//                    textNoSong.visibility = View.GONE
//                    recyclerView.visibility = View.VISIBLE
//                } else {
//                    textNoSong.visibility = View.VISIBLE
//                    recyclerView.visibility = View.GONE
//                }
//            }
//
//            override fun onFailure(call: Call<List<Songs>>, t: Throwable) {
//                Log.d("SSS", t.toString())
//            }
//
//        })
//    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}