package com.prudhvir3ddy.dailybugle.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

/**
 * data classes are POJO classes in kotlin
 */
data class DatabaseNews(
  val status: String,
  val totalResults: Int,
  val articles: List<UIDatabaseArticles>

)

/**
 * UIDatabaseArticles
 * because we are going to use this classes to show in the UI
 * and this class acts as database table
 */
@Entity(tableName = "articles")
data class UIDatabaseArticles(
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

/**
 * saved articles table
 *
 */
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

/**
 * converter classes
 * as we can't store the complex objects in room database
 */
class Converters {
  @TypeConverter
  fun fromArticleSourceToJson(value: DatabaseArticleSource): String {
    val moshi = Moshi.Builder()
      .build()
    val jsonAdapter = moshi.adapter(DatabaseArticleSource::class.java)

    return jsonAdapter.toJson(value)
  }

  @TypeConverter
  fun fromJsonToArticleSource(json: String): DatabaseArticleSource {
    val moshi = Moshi.Builder()
      .build()
    val jsonAdapter = moshi.adapter(DatabaseArticleSource::class.java)
    return jsonAdapter.fromJson(json)!!
  }
}

