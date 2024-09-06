package com.rahul.newsapp.news.di

import com.rahul.newsapp.news.data.source.NewsByIdDataSource
import com.rahul.newsapp.news.data.source.NewsByIdDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by abrol at 06/09/24.
 */
/**
 * DI NewsByIdModule supplying the Top Headlines data source dependencies
 *
 * Created by abrol at 25/08/24.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class NewsByIdModule {

    /**
     * @param impl The News By Id data source implementation
     * @return A concrete impl of the News By Id data source
     */
    @Binds
    abstract fun provideNewsByIdDataSource(impl: NewsByIdDataSourceImpl): NewsByIdDataSource
}