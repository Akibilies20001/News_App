package com.merajhossen20001.newsapp.domain.usecases.app_entry

import com.merajhossen20001.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (
    private val localUserManager: LocalUserManager
){
    operator fun invoke():Flow<Boolean>{
        return localUserManager.readAppEntry()
    }//invoke allows object to be used like a function

}