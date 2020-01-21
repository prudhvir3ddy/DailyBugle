package com.prudhvir3ddy.dailybugle.utils

import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticleSource
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles
import com.prudhvir3ddy.dailybugle.network.data.ArticleSource
import com.prudhvir3ddy.dailybugle.network.data.ResponseArticles

fun ResponseArticles.asDatabaseModel(country: String?): UIDatabaseArticles {
    return UIDatabaseArticles(
        url = url,
        title = title,
        description = description,
        publishedAt = publishedAt,
        content = content,
        source = source.asDatabaseArticleSource(),
        urlToImage = urlToImage,
        country = country!!,
        author = author
    )

}

fun ArticleSource.asDatabaseArticleSource() = DatabaseArticleSource(
    id = id,
    name = name
)