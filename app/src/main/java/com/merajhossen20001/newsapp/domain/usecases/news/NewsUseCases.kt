package com.merajhossen20001.newsapp.domain.usecases.news

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val getBookmarkedArticles: GetBookmarkedArticles,
    val getArticle: GetArticle
)
