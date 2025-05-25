package com.merajhossen20001.newsapp.domain.usecases.app_entry

import com.merajhossen20001.newsapp.domain.manager.LocalUserManager

class SaveAppEntry (
    private val localUserManager: LocalUserManager
){
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    } //invoke allows object to be used like a function

}