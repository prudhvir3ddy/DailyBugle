package com.prudhvir3ddy.dailybugle.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.prudhvir3ddy.dailybugle.database.NewsDao
import com.prudhvir3ddy.dailybugle.database.NewsDatabase
import com.prudhvir3ddy.dailybugle.network.NewsApiService
import com.prudhvir3ddy.dailybugle.utils.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): NewsDao {
        return NewsDatabase.getInstance(context)
            .newsDatabaseDao
    }

}