package com.prudhvir3ddy.dailybugle.network.data

data class News(
  val status: String,
  val totalResults: Int,
  val articles: List<Articles>

)

data class Articles(
  val source: ArticleSource,
  val author: String?,
  val title: String,
  val description: String?,
  val url: String,
  val urlToImage: String?,
  val publishedAt: String,
  val content: String?
)

data class ArticleSource(
  val id: String?,
  val name: String
)
