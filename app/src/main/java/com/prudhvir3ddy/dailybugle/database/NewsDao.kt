package com.prudhvir3ddy.dailybugle.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prudhvir3ddy.dailybugle.database.data.DatabaseTopHeadlines

@Dao
interface NewsDao {

  /**
   * topheadlines
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTopHeadlines(articles: List<DatabaseTopHeadlines>)

  @Query("SELECT * FROM top_headlines WHERE `country`=:country")
  suspend fun getTopHeadlines(country: String): List<DatabaseTopHeadlines>

  @Query("DELETE FROM top_headlines WHERE `country`=:country")
  suspend fun clearTopHeadlines(country: String)

}