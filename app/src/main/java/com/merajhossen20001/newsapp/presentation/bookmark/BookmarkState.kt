package com.merajhossen20001.newsapp.presentation.bookmark

import com.merajhossen20001.newsapp.domain.newsmodels.Article

data class BookmarkState (
    val articles : List<Article> = emptyList()
)