package com.merajhossen20001.newsapp.di

import android.app.Application
import androidx.room.Room
import com.merajhossen20001.newsapp.data.local.NewsDao
import com.merajhossen20001.newsapp.data.local.NewsDatabase
import com.merajhossen20001.newsapp.data.local.NewsTypeConverter
import com.merajhossen20001.newsapp.data.manager.LocalUserManagerImplementation
import com.merajhossen20001.newsapp.data.remote.NewsApi
import com.merajhossen20001.newsapp.data.repository.NewsRepositoryImplementation
import com.merajhossen20001.newsapp.domain.manager.LocalUserManager
import com.merajhossen20001.newsapp.domain.repository.NewsRepository
import com.merajhossen20001.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.merajhossen20001.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.merajhossen20001.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.merajhossen20001.newsapp.domain.usecases.news.DeleteArticle
import com.merajhossen20001.newsapp.domain.usecases.news.GetBookmarkedArticles
import com.merajhossen20001.newsapp.domain.usecases.news.GetNews
import com.merajhossen20001.newsapp.domain.usecases.news.NewsUseCases
import com.merajhossen20001.newsapp.domain.usecases.news.SearchNews
import com.merajhossen20001.newsapp.domain.usecases.news.GetArticle
import com.merajhossen20001.newsapp.domain.usecases.news.UpsertArticle
import com.merajhossen20001.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application // used because context is passed a parameter
    ): LocalUserManager = LocalUserManagerImplementation(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager)
    = AppEntryUseCases(
        saveAppEntry = SaveAppEntry(localUserManager),
        readAppEntry = ReadAppEntry(localUserManager)
    )


    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ):NewsRepository{
        return NewsRepositoryImplementation(newsApi,newsDao)
    }


    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            getBookmarkedArticles = GetBookmarkedArticles(newsRepository),
            getArticle = GetArticle(newsRepository)

        )
    }

     @Provides
     @Singleton
     fun provideNewsDatabase(
         application: Application
     ): NewsDatabase{

         return Room.databaseBuilder(
             context = application,
             klass = NewsDatabase::class.java,
             name = "news_database"
         ).addTypeConverter(NewsTypeConverter())
             .fallbackToDestructiveMigration().build()
     }


    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}