package com.prudhvir3ddy.dailybugle.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticleSource
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class ResponseNewsDatabaseTest {

    private lateinit var newsDao: NewsDao
    private lateinit var newsDatabase: NewsDatabase

    @get: Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb(){

        val context = InstrumentationRegistry.getInstrumentation().targetContext

        newsDatabase = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        newsDao = newsDatabase.newsDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        newsDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNews(){

        val expectedArticles = listOf(

            UIDatabaseArticles(
                author = "prudhvi",
                source = DatabaseArticleSource(
                    "1",
                    "boo"
                ),
                country = "in",
                title = "The Great",
                description = "I Said the great",
                url = "facebook.com",
                urlToImage = "facebook.com",
                publishedAt = "20-10-1999",
                content = "boo, boo boo"
            )

        )
        runBlocking {
            newsDao.insert(expectedArticles)
            val articles = newsDao.getAllArticles(country = "in")
            assertEquals(expectedArticles, articles)
        }
    }
}