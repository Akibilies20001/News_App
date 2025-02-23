package com.merajhossen20001.newsapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.merajhossen20001.newsapp.R
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.presentation.Dimensions
import com.merajhossen20001.newsapp.presentation.common.ArticleCardShimmerEffect
import com.merajhossen20001.newsapp.presentation.common.ArticleList
import com.merajhossen20001.newsapp.presentation.common.SearchBar
import com.merajhossen20001.newsapp.presentation.navgraph.Screen
import com.merajhossen20001.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalFoundationApi::class)
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
        //.padding(top = Dimensions.MediumPadding1)
        .statusBarsPadding()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null,
                Modifier
                    .padding(horizontal = Dimensions.SmallPadding)
                    .size(70.dp)
                    .clipToBounds()
            )
            Text(text = "Jaina Nei",
                color = colorResource(R.color.text_title), fontSize = 24.sp
            )
        }



        SearchBar(text = "",
            readOnly = true,
            onValueChange = {},
            onClick = { navigate(Screen.SearchScreen.route)},
            onSearch = {}
        )
        Spacer(modifier = Modifier.height(Dimensions.MediumPadding1))
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(Dimensions.MediumPadding1))

        ArticleList(articles = articles,
            onclick = { navigate(Screen.DetailsScreen.route)}
        )


    }

}


