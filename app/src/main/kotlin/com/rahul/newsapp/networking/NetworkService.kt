package com.rahul.newsapp.networking

import com.rahul.newsapp.headlines.data.model.TopHeadlinesNetworkEntity
import com.rahul.newsapp.source.data.model.NewsSourceEntity
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): TopHeadlinesNetworkEntity

    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourceEntity

    @GET("top-headlines")
    suspend fun getNewsBySources(@Query("sources") sources: String): TopHeadlinesNetworkEntity

    @GET("top-headlines")
    suspend fun getNewsByLanguage(@Query("language") languageCode: String): TopHeadlinesNetworkEntity

    @GET("everything")
    suspend fun getNewsByQueries(@Query("q") queries: String): TopHeadlinesNetworkEntity
}
