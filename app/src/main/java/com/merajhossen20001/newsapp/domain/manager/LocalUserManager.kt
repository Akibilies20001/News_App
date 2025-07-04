package com.merajhossen20001.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
    //suspend fun initializeAppEntryIfFirstLaunch()
}