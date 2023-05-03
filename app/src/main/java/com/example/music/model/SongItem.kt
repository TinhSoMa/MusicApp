package com.example.music.model

data class SongItem(
    val s_id: String,
    val s_name: String,
    val s_mp3: String,
    val s_image: String,
    val s_likes: String,
    val ar_name: String,
    val ar_image: String
) : java.io.Serializable
