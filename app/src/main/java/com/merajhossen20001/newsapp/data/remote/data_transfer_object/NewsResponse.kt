package com.merajhossen20001.newsapp.data.remote.data_transfer_object

import com.merajhossen20001.newsapp.domain.newsmodels.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)