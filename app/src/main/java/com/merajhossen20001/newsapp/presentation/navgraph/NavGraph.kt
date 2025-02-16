package com.merajhossen20001.newsapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.merajhossen20001.newsapp.presentation.onboarding.OnBoardingScreen
import com.merajhossen20001.newsapp.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(startDestination: String){
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Screen.AppStartNav.route,
            startDestination = Screen.OnboardingScreen.route){
            composable(route = Screen.OnboardingScreen.route){
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = {
                        viewModel.onEvent(it)
                    }
                )
            }
        }

        navigation(
            route = Screen.NewsNavigation.route,
            startDestination = Screen.NewsNavigatorScreen.route){
            //TODO
        }
    }

}