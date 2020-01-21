package com.prudhvir3ddy.dailybugle.injection

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.prudhvir3ddy.dailybugle.database.NewsDao
import com.prudhvir3ddy.dailybugle.database.NewsDatabase
import com.prudhvir3ddy.dailybugle.network.NewsApiService
import com.prudhvir3ddy.dailybugle.repository.Repository
import com.prudhvir3ddy.dailybugle.utils.BASE_URL
import com.prudhvir3ddy.dailybugle.viewmodels.HomeViewModel
import com.prudhvir3ddy.dailybugle.viewmodels.SearchViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory




val sharedPreferencesModule = module{

    single {
        provideSharedPreferences(get())
    }

    single {
        provideRoomDatabase(get())
    }
}

val viewModelModule = module {

    viewModel { HomeViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
}

val repositoryModule = module {

    factory {
        Repository(get(),get(), get())
    }
}
val networkModule = module {

    single {
        provideApiService(get())
    }
    single {
        provideMoshiConverterFactory()

    }

    single {

        provideRetrofit(get())
    }
}

fun provideSharedPreferences(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}

fun provideRoomDatabase(context: Context): NewsDao {
    return NewsDatabase.getInstance(context).newsDatabaseDao
}

fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory): Retrofit {
    return Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(moshiConverterFactory)
        .baseUrl(BASE_URL)
        .build()
}

fun provideMoshiConverterFactory(): MoshiConverterFactory {
    return MoshiConverterFactory.create(
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    )
}

fun provideApiService(retrofit: Retrofit): NewsApiService {
    return retrofit.create(NewsApiService::class.java)
}