package com.prudhvir3ddy.dailybugle.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.prudhvir3ddy.dailybugle.database.data.Converters
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles

/**
 * Main Database configuration of app here
 * it's a singleton class
 */
@Database(entities = [UIDatabaseArticles::class], version = 1, exportSchema = false)
@TypeConverters(value = [Converters::class])
abstract class NewsDatabase : RoomDatabase() {

    abstract val newsDatabaseDao: NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        /**
         * getting the database instance, this is common to whole app
         */
        fun getInstance(context: Context): NewsDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDatabase::class.java,
                        "news_db"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance

                }
                return instance

            }

        }

    }
}