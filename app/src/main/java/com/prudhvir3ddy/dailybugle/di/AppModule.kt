package com.prudhvir3ddy.dailybugle.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.prudhvir3ddy.dailybugle.database.NewsDao
import com.prudhvir3ddy.dailybugle.database.NewsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {



    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences{
    return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): NewsDao{
        return NewsDatabase.getInstance(context).newsDatabaseDao
    }


}