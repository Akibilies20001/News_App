package com.merajhossen20001.newsapp.presentation.search

import androidx.paging.PagingData
import com.merajhossen20001.newsapp.domain.newsmodels.Article
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
){

}
