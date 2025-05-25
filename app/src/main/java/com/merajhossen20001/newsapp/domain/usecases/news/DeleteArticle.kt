package com.merajhossen20001.newsapp.domain.usecases.news

import com.merajhossen20001.newsapp.data.local.NewsDao
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }

}