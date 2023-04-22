package com.example.music.service

import com.example.music.model.Ads
import com.example.music.model.Album
import com.example.music.model.PlayList
import com.example.music.model.TopicCategory
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("Service/select_ads.php")
    fun getDataAds(): Call<List<Ads>>

    @GET("Service/select_playlist.php")
    fun getDataPlayList(): Call<List<PlayList>>

    @GET("Service/select_topic_category.php")
    fun getDataTopicCategory(): Call<TopicCategory>

    @GET("Service/select_album.php")
    fun getDataAlbum(): Call<List<Album>>
}