package com.merajhossen20001.newsapp.presentation.search

sealed class SearchEvent {
    data class  UpdateSearchQuery(val searchQuery: String) : SearchEvent()

    object  SearchNews: SearchEvent()
}