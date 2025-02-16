package com.merajhossen20001.newsapp.di

import android.app.Application
import com.merajhossen20001.newsapp.data.manager.LocalUserManagerImplementation
import com.merajhossen20001.newsapp.domain.manager.LocalUserManager
import com.merajhossen20001.newsapp.domain.usecases.AppEntryUseCases
import com.merajhossen20001.newsapp.domain.usecases.ReadAppEntry
import com.merajhossen20001.newsapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}