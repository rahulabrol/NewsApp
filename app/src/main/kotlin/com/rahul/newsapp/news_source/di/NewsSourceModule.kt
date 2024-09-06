package com.rahul.newsapp.news_source.di

import com.rahul.newsapp.news_source.data.source.NewsSourceDataSource
import com.rahul.newsapp.news_source.data.source.NewsSourceDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * DI NewsSourceModule supplying the News Source data source dependencies
 *
 * Created by abrol at 25/08/24.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class NewsSourceModule {

    /**
     * @param impl The Top Headlines data source implementation
     * @return A concrete impl of the Top Headlines data source
     */
    @Binds
    abstract fun provideNewsSourceDataSource(impl: NewsSourceDataSourceImpl): NewsSourceDataSource
}