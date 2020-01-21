package com.prudhvir3ddy.dailybugle.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.prudhvir3ddy.dailybugle.network.data.News
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

data class DatabaseNews(
    val status: String,
    val totalResults: Int,
    val articles: List<DatabaseArticles>

)

@Entity(tableName = "articles")
data class DatabaseArticles(
    val source: DatabaseArticleSource,
    val author: String?,
    val title: String,
    val description: String?,
    val country: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

@Entity(tableName = "saved_articles")
data class SavedArticles(
    val source: DatabaseArticleSource,
    val author: String?,
    val title: String,
    val description: String?,
    val country: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

@JsonClass(generateAdapter = true)
data class DatabaseArticleSource(
    val id: String?,
    val name: String
)

class Converters{
    @TypeConverter
    fun fromArticleSourceToJson(value: DatabaseArticleSource): String{
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(DatabaseArticleSource::class.java)

        return jsonAdapter.toJson(value)
    }

    @TypeConverter
    fun fromJsonToArticleSource(json: String): DatabaseArticleSource {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(DatabaseArticleSource::class.java)
        return jsonAdapter.fromJson(json)!!
    }
}

