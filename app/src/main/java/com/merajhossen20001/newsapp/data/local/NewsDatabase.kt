package com.merajhossen20001.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.merajhossen20001.newsapp.domain.newsmodels.Article

@Database(entities = [Article::class], version = 2)
@TypeConverters(NewsTypeConverter::class)

abstract class NewsDatabase:RoomDatabase() {

    abstract val newsDao: NewsDao

}