package com.merajhossen20001.newsapp.presentation.detail

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.merajhossen20001.newsapp.R
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.domain.newsmodels.Source
import com.merajhossen20001.newsapp.presentation.Dimensions
import com.merajhossen20001.newsapp.presentation.detail.components.DetailTopAppBar
import com.merajhossen20001.newsapp.presentation.detail.components.DetailEvent
import com.merajhossen20001.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailScreen(
    article: Article,
    event: (DetailEvent)-> Unit,
    navigateUp: ()-> Unit
) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        DetailTopAppBar(
            onBack = navigateUp,
            onShare = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBrowserSearch = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onSaveBookmark = { event(DetailEvent.UpsertDeleteArticle(article)) }
        )

        LazyColumn {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(248.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.height(Dimensions.MediumPadding1))
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(
                        id = R.color.text_title
                    )

                )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(
                        id = R.color.text_title
                    )

                )

            }
        }

    }
}


@Preview(
    name = "Light Mode",
    showBackground = true,

)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewDetailScreen() {
    NewsAppTheme {
        Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)){
    DetailScreen(
        article = Article(
            title = "Sample Title",
            description = "This is a description.",
            url = "https://example.com",
            urlToImage = "https://via.placeholder.com/600x400.png",
            content = "Some article content goes here.",
            author = "Jane Doe",
            publishedAt = "2025-04-09T12:00:00Z",
            source = Source("dasf","Sample News")) ,
        event = {},
        navigateUp = {}
    )}}
}
