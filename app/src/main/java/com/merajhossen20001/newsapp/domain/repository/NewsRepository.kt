package com.merajhossen20001.newsapp.domain.repository

import androidx.paging.PagingData
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>):Flow<PagingData<Article>>
}