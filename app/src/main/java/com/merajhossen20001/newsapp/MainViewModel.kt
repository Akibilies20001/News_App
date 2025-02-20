package com.merajhossen20001.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merajhossen20001.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.merajhossen20001.newsapp.presentation.navgraph.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Screen.AppStartNav.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach {shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen){
                startDestination = Screen.NewsNavigation.route
            }else{
                startDestination = Screen.AppStartNav.route
            }
            delay(300)
            splashCondition = false

        }.launchIn(viewModelScope)

    }
}