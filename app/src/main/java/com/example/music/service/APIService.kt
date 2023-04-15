package com.example.music.service

class APIService {
    companion object {
        private const val BASE_URL = "http://mp3app.epizy.com/Server/"
        fun getService(): DataService {
            return APIRetrofitClient.getClient(BASE_URL).create(DataService::class.java)
        }
    }
}