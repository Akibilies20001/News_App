package com.merajhossen20001.newsapp.domain.usecases.news

import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.domain.repository.NewsRepository

class GetArticle (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article?{
        return newsRepository.getArticle(url)
    }

}