package com.prudhvir3ddy.dailybugle.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.prudhvir3ddy.dailybugle.database.NewsDao
import com.prudhvir3ddy.dailybugle.database.NewsDatabase
import com.prudhvir3ddy.dailybugle.network.NewsApiService
import com.prudhvir3ddy.dailybugle.utils.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * all dependencies of entire application goes here
 */
@Module
class AppModule {

  //providing retrofit, used for networking
  @Provides
  @Singleton
  fun provideRetrofit(moshi: Moshi): Retrofit {
    return Retrofit.Builder()
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .baseUrl(BASE_URL)
      .build()
  }

  //providing moshi, helps in type converting
  @Provides
  @Singleton
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()
  }

  //providing api service, this helps to call network functions in this service
  @Provides
  @Singleton
  fun provideApiService(retrofit: Retrofit): NewsApiService {
    return retrofit.create(NewsApiService::class.java)
  }

  //providing sharedpreferences for storing country value
  @Provides
  @Singleton
  fun provideSharedPreferences(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
  }

  //providing room database dao for accessing data in database
  @Singleton
  @Provides
  fun provideRoomDatabase(context: Context): NewsDao {
    return NewsDatabase.getInstance(context)
      .newsDatabaseDao
  }

}