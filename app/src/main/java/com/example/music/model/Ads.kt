package com.example.music.model
import java.io.Serializable

data class Ads(
    var ads_id: String,
    var s_id: String,
    var ar_id: String,
    var alb_id: String,
    var ads_content: String,
    var s_name: String,
    var s_image: String,
    var ar_image: String,
    var ar_name: String
) : Serializable