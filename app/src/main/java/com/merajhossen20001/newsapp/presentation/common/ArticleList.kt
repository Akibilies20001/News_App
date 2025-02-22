package com.merajhossen20001.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.presentation.Dimensions

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles : LazyPagingItems<Article>,
    onclick: (Article)-> Unit
){
    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult){
        LazyColumn (
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimensions.MediumPadding1),
            contentPadding = PaddingValues(Dimensions.SmallPadding)
        ){
            items(count = articles.itemCount){ item->
                articles[item]?.let{
                    article->
                    ArticleCard(article = article, onclick = {onclick(article)} )
                }
            }
        }
    }

}


@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else-> null
    }

    return when{
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error!= null ->{
            EmptyScreen()
            false
        }
        else ->{
            true
        }
    }
}



@Composable
private fun ShimmerEffect(){
    Column(verticalArrangement = Arrangement.spacedBy(Dimensions.MediumPadding1)) {
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = Dimensions.MediumPadding1)
            )
        }
    }

}