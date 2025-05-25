package com.merajhossen20001.newsapp.presentation.bottom_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.merajhossen20001.newsapp.R
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.presentation.bookmark.BookmarkScreen
import com.merajhossen20001.newsapp.presentation.bookmark.BookmarkViewModel
import com.merajhossen20001.newsapp.presentation.bottom_navigation.components.BottomNavigationItem
import com.merajhossen20001.newsapp.presentation.bottom_navigation.components.NewsBottomNavigation
import com.merajhossen20001.newsapp.presentation.detail.DetailScreen
import com.merajhossen20001.newsapp.presentation.detail.DetailsViewModel
import com.merajhossen20001.newsapp.presentation.home.HomeScreen
import com.merajhossen20001.newsapp.presentation.home.HomeViewModel
import com.merajhossen20001.newsapp.presentation.navgraph.Screen
import com.merajhossen20001.newsapp.presentation.search.SearchScreen
import com.merajhossen20001.newsapp.presentation.search.SearchViewModel

@Composable
fun NewsNavigator(){

    val bottomNewsNavigationItems = remember {
         listOf(
            BottomNavigationItem(icon = R.drawable.baseline_home_24, name = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, name = "Search"),
            BottomNavigationItem(icon = R.drawable.baseline_bookmark_border_24, name = "Bookmark")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = remember (key1 = backStackState){
        when(backStackState?.destination?.route){
            Screen.HomeScreen.route -> 0
            Screen.SearchScreen.route -> 1
            Screen.BookmarksScreen.route -> 2
            else -> 0
    }


    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Screen.HomeScreen.route ||
                backStackState?.destination?.route == Screen.SearchScreen.route ||
                backStackState?.destination?.route == Screen.BookmarksScreen.route
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNewsNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Screen.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Screen.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Screen.BookmarksScreen.route
                            )
                        }

                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()

        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)){
            composable(
                route = Screen.HomeScreen.route){
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(navController = navController,
                            route = Screen.SearchScreen.route)
                    },
                    navigateToDetail = {article->
                        navigateToDetail(
                            navController = navController,
                            article = article
                        )


                    }
                )
            }

            composable(route = Screen.SearchScreen.route){
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value

                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetail = {
                        navigateToDetail(navController = navController,
                            article = it)

                    })
            }
            composable(route = Screen.DetailsScreen.route){
                val viewModel: DetailsViewModel = hiltViewModel()
                //viewModel.sideEffect
                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                    ?.let {article ->
                        DetailScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = {
                                navController.navigateUp()
                            }
                        )

                    }
            }

            composable(route = Screen.BookmarksScreen.route){
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    state = state,
                    navigateToDetail = {article ->
                        navigateToDetail(
                            navController = navController,
                            article = article
                        )

                    }
                )
            }




        }
    }


}

private fun navigateToTab(navController: NavController, route: String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true

        }
    }
}

private fun navigateToDetail(navController: NavController,
                             article: Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Screen.DetailsScreen.route
    )
}