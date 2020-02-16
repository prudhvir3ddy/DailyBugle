package com.prudhvir3ddy.dailybugle.utils

import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticleSource
import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticles
import com.prudhvir3ddy.dailybugle.network.data.ArticleSource
import com.prudhvir3ddy.dailybugle.network.data.Articles

fun Articles.asDatabaseModel(country: String?): DatabaseArticles {
    return DatabaseArticles(
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