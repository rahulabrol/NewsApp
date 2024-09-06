package com.rahul.newsapp.top_headlines.data.di

import com.rahul.newsapp.top_headlines.data.source.TopHeadlinesDataSource
import com.rahul.newsapp.top_headlines.data.source.TopHeadlinesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * DI NewsSourceModule supplying the NewsSource data source dependencies
 *
 * Created by abrol at 25/08/24.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class TopHeadlinesModule {

    /**
     * @param impl The News Source data source implementation
     * @return A concrete impl of the NewsSource data source
     */
    @Binds
    abstract fun provideTopHeadlinesDataSource(impl: TopHeadlinesDataSourceImpl): TopHeadlinesDataSource
}