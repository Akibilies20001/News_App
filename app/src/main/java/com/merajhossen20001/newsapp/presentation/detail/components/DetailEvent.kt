package com.merajhossen20001.newsapp.presentation.detail.components

import com.merajhossen20001.newsapp.domain.newsmodels.Article

sealed class DetailEvent{
    data class UpsertDeleteArticle(val article: Article): DetailEvent()

    object RemoveSideEffect : DetailEvent()
}

