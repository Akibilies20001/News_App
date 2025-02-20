package com.merajhossen20001.newsapp.di

import android.app.Application
import com.merajhossen20001.newsapp.data.manager.LocalUserManagerImplementation
import com.merajhossen20001.newsapp.data.remote.NewsApi
import com.merajhossen20001.newsapp.data.repository.NewsRepositoryImplementation
import com.merajhossen20001.newsapp.domain.manager.LocalUserManager
import com.merajhossen20001.newsapp.domain.repository.NewsRepository
import com.merajhossen20001.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.merajhossen20001.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.merajhossen20001.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.merajhossen20001.newsapp.domain.usecases.news.GetNews
import com.merajhossen20001.newsapp.domain.usecases.news.NewsUseCases
import com.merajhossen20001.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
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
    fun provideNewsRepository(newsApi: NewsApi):NewsRepository{
        return NewsRepositoryImplementation(newsApi)
    }


    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases{
        return NewsUseCases(getNews = GetNews(newsRepository))
    }
}