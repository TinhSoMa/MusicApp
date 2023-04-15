package com.example.music.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

class Ads : ArrayList<Ads.AdsItem>(){
    @Keep
    data class AdsItem(
        @SerializedName("ads_content")
        val adsContent: String?,
        @SerializedName("ads_id")
        val adsId: String?,
        @SerializedName("s_image")
        val sImage: String?,
        @SerializedName("s_name")
        val sName: String?
    )
}