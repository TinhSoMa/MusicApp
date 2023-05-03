package com.example.music.model
import java.io.Serializable
data class PlayList(
    val pls_id: String,
    val pls_name: String,
    val pls_background: String,
    val pls_icon: String
): Serializable
