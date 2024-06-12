package com.ozman.myappinitial2.data.remote

import com.ozman.myappinitial2.data.response.Item
import retrofit2.http.GET

/**
 * ApiService interface defining the endpoint to fetch items.
 * This interface is used by Retrofit to make network calls.
 */
interface ApiService {

    /**
     * Endpoint to fetch items from the server.
     * @return List of items fetched from the server.
     */
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}