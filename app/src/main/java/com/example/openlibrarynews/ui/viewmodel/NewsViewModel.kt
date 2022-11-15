package com.example.openlibrarynews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.openlibrarynews.utils.Config
import com.example.openlibrarynews.data.model.NewsItem
import com.example.openlibrarynews.data.remote.ApiHelper
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val mainRepository: ApiHelper
) : ViewModel() {

     var  result: LiveData<List<NewsItem>> = MutableLiveData()


    /**
     * function to fetch articles
     */

     fun fetchNewsList(){
        result = mainRepository.fetchNews(
            hashMapOf(
                Config.apiKey to Config.apiKeyValue
            ))
    }
}