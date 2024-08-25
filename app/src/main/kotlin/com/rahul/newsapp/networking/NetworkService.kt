package com.rahul.newsapp.networking


import com.rahul.newsapp.top_headlines.data.model.NewsSourcesEntity
import com.rahul.newsapp.top_headlines.data.model.TopHeadlinesEntity
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
    ): TopHeadlinesEntity

    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesEntity

    @GET("top-headlines")
    suspend fun getNewsBySources(@Query("sources") sources: String): TopHeadlinesEntity

    @GET("top-headlines")
    suspend fun getNewsByLanguage(@Query("language") languageCode: String): TopHeadlinesEntity

    @GET("everything")
    suspend fun getNewsByQueries(@Query("q") queries: String): TopHeadlinesEntity

}