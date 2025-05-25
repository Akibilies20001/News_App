package com.merajhossen20001.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.presentation.Dimensions
import com.merajhossen20001.newsapp.presentation.common.ArticleList
import com.merajhossen20001.newsapp.presentation.navgraph.Screen

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetail: (Article) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(Dimensions.MediumPadding1)
    ) {
        Text(text = "Bookmarks",
            style = MaterialTheme.typography.titleSmall)

        Spacer(Modifier.height(Dimensions.MediumPadding1))
        
        ArticleList(
            articles = state.articles,
            onclick = {navigateToDetail(it)}
        )





    }
}