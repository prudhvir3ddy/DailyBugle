package com.prudhvir3ddy.dailybugle.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticles

@Dao
interface NewsDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articles: List<DatabaseArticles>)

    @Query("SELECT * FROM articles WHERE country=:country")
    suspend fun getAllArticles(country: String): List<DatabaseArticles>

    @Query("DELETE FROM articles WHERE country=:country")
    suspend fun clear(country: String)
}