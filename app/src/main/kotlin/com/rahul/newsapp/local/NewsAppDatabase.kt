package com.rahul.newsapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rahul.newsapp.local.dao.CountryDao
import com.rahul.newsapp.local.dao.LanguageDao
import com.rahul.newsapp.local.dao.NewsSourceDao
import com.rahul.newsapp.local.dao.TopHeadlinesDao
import com.rahul.newsapp.local.entity.Article

/**
 * Created by abrol at 25/08/24.
 */
@Database(
    entities = [Article::class/*, NewsSource::class, LanguageEntity::class, Country::class*/],
    version = 1,
    exportSchema = false
)
abstract class NewsAppDatabase : RoomDatabase() {
    abstract fun topHeadlinesDao(): TopHeadlinesDao
    abstract fun newsSourceDao(): NewsSourceDao
    abstract fun languageDao(): LanguageDao
    abstract fun countryDao(): CountryDao
}
