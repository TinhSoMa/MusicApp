package com.example.music.service

import com.example.music.model.Ads
import retrofit2.Call
import retrofit2.http.GET

interface DataService {
    @GET(/* value = */ "select_ads.php")
    fun getDataAds(): Call<List<Ads>>
}