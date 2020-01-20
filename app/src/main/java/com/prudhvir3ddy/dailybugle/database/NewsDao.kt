package com.prudhvir3ddy.dailybugle.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticles

@Dao
interface NewsDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articles: List<DatabaseArticles>)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<DatabaseArticles>

    @Query("DELETE FROM articles")
    suspend fun clear()
}