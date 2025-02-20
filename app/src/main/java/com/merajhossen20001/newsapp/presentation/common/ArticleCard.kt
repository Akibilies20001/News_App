package com.merajhossen20001.newsapp.presentation.common


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.merajhossen20001.newsapp.R
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.presentation.Dimensions
import com.merajhossen20001.newsapp.presentation.Dimensions.ArticleCardSize
import com.merajhossen20001.newsapp.util.Constants

@Composable
fun ArticleCard(modifier: Modifier = Modifier,
                article: Article,
                onclick: () -> Unit
){
    val context = LocalContext.current
    Row (modifier = modifier.clickable { onclick() }){
        AsyncImage(modifier = Modifier
            .size(ArticleCardSize)
            .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context =context )
                .data(article.urlToImage)
                .build(),
            contentDescription = article.title)

    }
    Column (verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .padding(horizontal = Dimensions.SmallPadding)
            .height(ArticleCardSize)
    ){
        Text(
            text = article.title,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource( id = R.color.text_title),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis

        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = article.source.name,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.body)
            )

            Spacer(Modifier.width( Dimensions.ExtraSmallPadding))

            Icon(painter = painterResource(R.drawable.baseline_access_time_24),
                modifier = Modifier.size(Dimensions.DefaultIconSize),
                tint = colorResource(R.color.body),
                contentDescription = "Time Posted"
            )

            Spacer(Modifier.width( Dimensions.ExtraSmallPadding))
            Text(
                text = article.publishedAt,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.body)
            )


        }
    }
}