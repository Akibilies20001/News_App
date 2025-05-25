package com.merajhossen20001.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.presentation.Dimensions
import com.merajhossen20001.newsapp.presentation.common.ArticleList
import com.merajhossen20001.newsapp.presentation.common.SearchBar
import com.merajhossen20001.newsapp.presentation.navgraph.Screen

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent)-> Unit,
    navigateToDetail : (Article) -> Unit
){
    Column(
        modifier = Modifier
            .padding(
            top = Dimensions.MediumPadding1,
            start = Dimensions.MediumPadding1,
            end = Dimensions.MediumPadding1
        ).fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {
                event(SearchEvent.UpdateSearchQuery(it))
            },
            onSearch = {event(SearchEvent.SearchNews)},

        )

        Spacer(Modifier.height(Dimensions.MediumPadding1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles = articles,
                onclick = {navigateToDetail(it)})
        }

    }

}