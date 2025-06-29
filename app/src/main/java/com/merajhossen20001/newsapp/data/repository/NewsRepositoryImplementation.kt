package com.merajhossen20001.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.merajhossen20001.newsapp.data.local.NewsDao
import com.merajhossen20001.newsapp.data.remote.NewsApi
import com.merajhossen20001.newsapp.data.remote.NewsPagingSource
import com.merajhossen20001.newsapp.data.remote.SearchNewsPagingSource
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import com.merajhossen20001.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImplementation(
    val newsApi: NewsApi,
    val newsDao: NewsDao
    ):NewsRepository {
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

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString (separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticles().onEach { it.reversed() }
    }

    override suspend fun getArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }
}