package com.rahul.newsapp.top_headlines.data.di

import com.rahul.newsapp.top_headlines.data.source.TopHeadlinesDataSource
import com.rahul.newsapp.top_headlines.data.source.TopHeadlinesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * DI TopHeadlineModule supplying the Top Headlines data source dependencies
 *
 * Created by abrol at 25/08/24.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class TopHeadlineModule {

    /**
     * @param impl The Top Headlines data source implementation
     * @return A concrete impl of the Top Headlines data source
     */
    @Binds
    abstract fun provideTopHeadlinesDataSource(impl: TopHeadlinesDataSourceImpl): TopHeadlinesDataSource
}