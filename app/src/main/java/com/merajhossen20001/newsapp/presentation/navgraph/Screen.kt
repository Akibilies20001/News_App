package com.merajhossen20001.newsapp.presentation.navgraph

sealed class Screen(val route: String) {
    object OnboardingScreen : Screen ("onBoardingScreen")
    object HomeScreen : Screen ("homeScreen")
    object SearchScreen : Screen ("searchScreen")
    object BookmarksScreen : Screen ("bookmarksScreen")
    object DetailsScreen : Screen ("detailsScreen")
    object AppStartNav : Screen ("appStartNav")
    object NewsNavigation : Screen ("newsNavigation")
    object NewsNavigatorScreen : Screen ("newsNavigatorScreen")
}