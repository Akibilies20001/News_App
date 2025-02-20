package com.merajhossen20001.newsapp.data.remote

import com.merajhossen20001.newsapp.data.remote.data_transfer_object.NewsResponse
import com.merajhossen20001.newsapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}