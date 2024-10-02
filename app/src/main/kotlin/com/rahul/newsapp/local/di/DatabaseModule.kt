package com.rahul.newsapp.local.di

import android.content.Context
import androidx.room.Room
import com.rahul.newsapp.local.NewsAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by abrol at 25/08/24.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideNewsAppDatabase(
        @ApplicationContext context: Context
    ): NewsAppDatabase = Room.databaseBuilder(
        context,
        NewsAppDatabase::class.java,
        "news-app-database"
    ).build()

    @Provides
    @Singleton
    fun provideTopHeadlinesDao(newsAppDatabase: NewsAppDatabase) = newsAppDatabase.topHeadlinesDao()

    @Provides
    @Singleton
    fun provideNewsSourceDao(newsAppDatabase: NewsAppDatabase) = newsAppDatabase.newsSourceDao()

    @Provides
    @Singleton
    fun provideLanguageDao(newsAppDatabase: NewsAppDatabase) = newsAppDatabase.languageDao()

    @Provides
    @Singleton
    fun provideCountryDao(newsAppDatabase: NewsAppDatabase) = newsAppDatabase.countryDao()
}
