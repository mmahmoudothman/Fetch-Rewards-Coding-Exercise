package com.ozman.myappinitial2.data.remote

import com.ozman.myappinitial2.data.response.Item
import retrofit2.http.GET

interface ApiService {

    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}