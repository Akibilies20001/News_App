package com.merajhossen20001.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow


class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}