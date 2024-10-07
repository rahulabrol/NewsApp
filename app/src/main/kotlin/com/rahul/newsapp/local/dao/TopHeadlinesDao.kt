package com.rahul.newsapp.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rahul.newsapp.local.entity.LocalArticle
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for cached articles.
 *
 * Created by abrol at 25/08/24.
 */
@Dao
interface TopHeadlinesDao {

    /**
     * Inserts a list of cached articles into the database.
     * If an article with the same ID already exists, it will be replaced.
     * @param articles The list of [LocalArticle] objects to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<LocalArticle>)

    /**
     * Gets all cached articles as a [Flow].
     * @return A [Flow] emitting a [List] of [LocalArticle] objects.
     */
    @Query("SELECT * FROM cached_articles")
    fun getAllCachedArticlesFlow(): Flow<List<LocalArticle>>

    /**
     * Gets a cached article by its ID as a [Flow].
     * @param id The ID of the article to retrieve.
     * @return A [Flow] emitting the [LocalArticle] if found.
     */
    @Query("SELECT * FROM cached_articles WHERE article_id =:id")
    fun getCachedArticleByIdFlow(id: Int): Flow<LocalArticle?>

    /**
     * Clears all cached articles from the database.
     */
    @Query("DELETE FROM cached_articles")
    suspend fun clearCachedArticles()
}
