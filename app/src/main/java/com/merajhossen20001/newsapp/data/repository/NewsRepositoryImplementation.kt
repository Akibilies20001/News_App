package com.merajhossen20001.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.merajhossen20001.newsapp.data.remote.NewsApi
import com.merajhossen20001.newsapp.data.remote.NewsPagingSource
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImplementation(val newsApi: NewsApi):NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString (separator = ",")
                )
            }
        ).flow

    }
}