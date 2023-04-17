package com.example.music.model


import android.telephony.SignalStrength
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

data class Ads(
    var ads_id: Int,
    var s_id: Int,
    var ar_id: Int,
    var alb_id: Int,
    var ads_content: String,
    var s_name: String,
    var s_image: String,
    var ar_image: String
//    var s_name: String,
//    var s_time: Int
//    var id: Int,
//    var title: String,
//    var body: String
)