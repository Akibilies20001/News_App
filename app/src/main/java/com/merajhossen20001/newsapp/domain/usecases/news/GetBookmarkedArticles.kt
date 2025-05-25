package com.merajhossen20001.newsapp.domain.usecases.news

import com.merajhossen20001.newsapp.data.local.NewsDao
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetBookmarkedArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>>{
        return newsRepository.getArticles()
    }
}