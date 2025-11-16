package com.ottapp.data.repository

import com.ottapp.core.network.ApiService
import com.ottapp.data.model.HomeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(
    private val apiService: ApiService
) {
    
    suspend fun getHomeContent(): Result<HomeResponse> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getHomeContent()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
