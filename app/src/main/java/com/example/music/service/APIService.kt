package com.example.music.service

import com.example.music.model.*
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @GET("Service/select_ads.php")
    fun getDataAds(): Call<List<Ads>>

    @GET("Service/select_playlist.php")
    fun getDataPlayList(): Call<List<PlayList>>

    @GET("Service/select_topic_category.php")
    fun getDataTopicCategory(): Call<TopicCategory>

    @GET("Service/select_album.php")
    fun getDataAlbum(): Call<List<Album>>

    @GET("Service/select_songs.php")
    fun getDataSongs(): Call<List<Songs>>

    @FormUrlEncoded
    @POST("Service/select_list_ads.php")
    fun getDataListAds(@Field("ads_id") ads_id: String): Call<List<Songs>>

    @FormUrlEncoded
    @POST("Service/select_list_ads.php")
    fun getDataPlayListSong(@Field("pls_id") pls_id: String): Call<List<Songs>>
    @FormUrlEncoded
    @POST("Service/okoko.php")
    fun getDataSearchSong(@Field("name") name: String): Call<List<Songs>>
    @GET("Service/search_song.php")
    fun getDataSong(): Call<List<Songs>>

    @FormUrlEncoded
    @POST("Service/check_login.php")
    fun getCheckLogin(@Field("user") user: String): Call<List<Accout>>
}