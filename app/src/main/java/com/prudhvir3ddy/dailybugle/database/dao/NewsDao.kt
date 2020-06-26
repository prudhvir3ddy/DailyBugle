package com.prudhvir3ddy.dailybugle.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles

/**
 * Dao - data access objects
 *
 * we got queries in this class to get data out of database
 */
@Dao
interface NewsDao {

  /**
   * for inserting articles fetched from internet
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(articles: List<UIDatabaseArticles>)

  /**
   * for getting all articles from database
   */
  @Query("SELECT * FROM articles WHERE country=:country")
  suspend fun getAllArticles(country: String): List<UIDatabaseArticles>

  /**
   * clearing the database to fill with new data
   */
  @Query("DELETE FROM articles WHERE country=:country")
  suspend fun clear(country: String)
}