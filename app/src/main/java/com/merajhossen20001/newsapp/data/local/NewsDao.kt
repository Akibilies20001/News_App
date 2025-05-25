package com.merajhossen20001.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  upsert(article: Article){

    }

    @Delete
    suspend fun delete(article: Article){

    }

    @Query("SELECT * from Article")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * from Article WHERE url = :url")
    suspend fun getArticle(url: String):Article?

}