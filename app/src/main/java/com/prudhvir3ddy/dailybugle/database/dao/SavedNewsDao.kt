package com.prudhvir3ddy.dailybugle.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prudhvir3ddy.dailybugle.database.data.SavedArticles

/**
 * Dao - data access objects
 *
 * we got queries in this class to get data out of database
 */
@Dao
interface SavedNewsDao {

  /**
   * for inserting article marked as favourite
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFav(articles: List<SavedArticles>)

  /**
   * for getting all fav articles from database
   */
  @Query("SELECT * FROM saved_articles")
  suspend fun getAllFav(): List<SavedArticles>

  /**
   * unmark as fav in database
   */
  @Query("DELETE FROM saved_articles WHERE url=:url")
  suspend fun removeFav(url: String)
}