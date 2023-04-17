package com.example.music.service

import com.example.music.model.Ads
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("Service/select_ads.php")
    fun getDataAds(): Call<List<Ads>>
}