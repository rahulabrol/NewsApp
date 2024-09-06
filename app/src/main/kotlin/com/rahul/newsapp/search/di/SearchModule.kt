package com.rahul.newsapp.search.di


import com.rahul.newsapp.search.data.source.SearchDataSource
import com.rahul.newsapp.search.data.source.SearchDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by abrol at 06/09/24.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule {
    /**
     * @param impl The Search data source implementation
     * @return A concrete impl of the Search data source
     */
    @Binds
    abstract fun provideSearchDataSource(impl: SearchDataSourceImpl): SearchDataSource
}