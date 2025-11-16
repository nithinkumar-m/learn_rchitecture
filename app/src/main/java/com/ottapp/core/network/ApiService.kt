package com.ottapp.core.network

import com.ottapp.data.model.HomeResponse
import retrofit2.http.GET

interface ApiService {
    
    @GET("home.json")
    suspend fun getHomeContent(): HomeResponse
}
