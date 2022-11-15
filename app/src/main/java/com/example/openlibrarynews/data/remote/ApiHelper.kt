package com.example.openlibrarynews.data.remote

import androidx.lifecycle.LiveData
import com.example.openlibrarynews.data.model.NewsItem

interface ApiHelper {
    fun fetchNews(queryMap: HashMap<String, String>): LiveData<List<NewsItem>>
}