package com.example.music.service

object DataService {
    private const val BASE_URL = "https://mp3-application.000webhostapp.com/"
    fun getService(): APIService {
        return APIRetrofitClient.getClient(BASE_URL).create(APIService::class.java)
    }
}