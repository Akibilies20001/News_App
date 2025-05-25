package com.merajhossen20001.newsapp.presentation.detail.components

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.merajhossen20001.newsapp.R
import com.merajhossen20001.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(
    onBack: () -> Unit,
    onBrowserSearch: () -> Unit,
    onShare: () -> Unit,
    onSaveBookmark: () -> Unit

){
    TopAppBar(
        title = {},
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(R.color.body),
            navigationIconContentColor = colorResource(R.color.body)

        ),
        navigationIcon = {
            IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "BackButton")
            }

        },
        actions = {
            IconButton(onClick = onShare) {
                Icon(Icons.Default.Share,
                    contentDescription = "BackButton")
            }
            IconButton(onClick = onSaveBookmark) {
                Icon(painter = painterResource( R.drawable.baseline_bookmark_border_24),
                    contentDescription = "BackButton")
            }
            IconButton(onClick = onBrowserSearch) {
            Icon(painter = painterResource( R.drawable.travel_explore_24px),
                contentDescription = "BackButton")
        }
        }
    )
}

@Preview(
    name = "Light Mode",
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun DetailTopAppBarPreview() {
    NewsAppTheme {
        Box(
           modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            DetailTopAppBar(
                onBack = {},
                onBrowserSearch = {},
                onShare = {},
                onSaveBookmark = {}
            )
        }
    }
}