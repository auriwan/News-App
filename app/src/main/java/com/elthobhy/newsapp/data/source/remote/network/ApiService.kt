package com.elthobhy.newsapp.data.source.remote.network

import com.elthobhy.newsapp.BuildConfig
import com.elthobhy.newsapp.data.source.remote.response.ArticlesItem
import com.elthobhy.newsapp.data.source.remote.response.ResponseCatalog
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines?apiKey=${BuildConfig.API_KEY}")
    fun getTopHeadlines(
        @Query("country") country: String
    ): Call<ResponseCatalog<ArticlesItem>>

    @GET("everything?apiKey=${BuildConfig.API_KEY}")
    fun getDomainNews(
        @Query("domains") domains: String
    ): Call<ResponseCatalog<ArticlesItem>>

    @GET("top-headlines?country=id&apiKey=${BuildConfig.API_KEY}")
    fun getCategoryNews(
        @Query("category") category: String
    ): Call<ResponseCatalog<ArticlesItem>>
}