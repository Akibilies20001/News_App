package com.merajhossen20001.newsapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.merajhossen20001.newsapp.R
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.presentation.Dimensions
import com.merajhossen20001.newsapp.presentation.common.ArticleCardShimmerEffect
import com.merajhossen20001.newsapp.presentation.navgraph.Screen
import com.merajhossen20001.newsapp.ui.theme.NewsAppTheme

@Composable
fun HomeScreen (
    articles: LazyPagingItems<Article>,
    navigate : (String)-> Unit
){
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString (separator = " \uD83d\uDFE5 "){ it.title }

            }else{
                ""
            }
        }
    }

    Column( modifier = Modifier
        .fillMaxSize()
        .padding(top = Dimensions.MediumPadding1)
        .statusBarsPadding()
    ) {
        Image(painter = painterResource(R.drawable.ic_logo),
            contentDescription = null,
            Modifier.width(150.dp).height(30.dp).padding(horizontal = Dimensions.MediumPadding1))

    }

}


